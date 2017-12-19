/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.Streams;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.ArrayUtils;

/**
 * * A <code>InputStream</code> wraps a FileInputStream. It obtains input bytes
 * from a file in a file system. What files are available depends on the host
 * environment.
 *
 *
 * @author Charles Hamilton
 */
public class InputStream implements Closeable {

    //private final InputStreamReader inputStreamReader;
    private final FileInputStream fileInputStream;
    private final long fileLength;
    private long currentPos = 0;
    private String encoding;

    /**
     * An <code>enum</code> for choosing the endian of the stream
     */
    public enum ENDIAN {

        /**
         * most significant byte, which is the byte containing the most
         * significant bit, is stored first
         */
        BIG_ENDIAN(0),
        /**
         * least significant byte, which is the byte containing the least
         * significant bit, is stored first
         */
        LITTLE_ENDIAN(1);

        private int val;

        private ENDIAN(int val) {
            this.val = val;
        }

        /**
         * Gets the value of the <code>enum</code>
         *
         * @return <code>int</code>
         */
        public int getVal() {
            return this.val;
        }
    }

    private ENDIAN endian = ENDIAN.BIG_ENDIAN;

    /**
     * sets the endian value
     *
     * @param endian sets big endian or little endian
     */
    public void setEndian(ENDIAN endian) {
        this.endian = endian;
    }

    /**
     * gets the endian value
     *
     * @return <code>ENDIAN</code>
     */
    public ENDIAN getEndian() {
        return this.endian;
    }

    /**
     * Constructs a new <code>InputStream</code> with a default encoding of UTF
     * 8
     *
     * @param file file to write to
     * @throws Exception for other errors
     * @throws NullPointerException if file is null
     */
    public InputStream(File file) throws NullPointerException,Exception {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        fileInputStream = new FileInputStream(file);
        encoding = "UTF8";
        fileLength = file.length();
    }

    /**
     * Constructs a new <code>InputStream</code> with the specified encoding
     *
     * @param file file to read from
     * @param charsetName Encoding name
     * @throws Exception any throwable error encountered
     */
    public InputStream(File file, String charsetName) throws Exception {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        fileInputStream = new FileInputStream(file);
        encoding = charsetName;
        fileLength = file.length();
    }

    /**
     * Constructs a new <code>InputStream</code> with the specified encoding
     *
     * @param stream <code>FileInputStream</code> to read from
     * @param charsetName name of the character set to use
     * @throws Exception
     * <p>
     * <strong>Character Set Names</strong><br>
     * US-ASCII - Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin
     * block of the Unicode character set<br>
     * ISO-8859-1 - ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1<br>
     * UTF-8 - Eight-bit UCS Transformation Format<br>
     * UTF-16BE - Sixteen-bit UCS Transformation Format, big-endian byte
     * order<br>
     * UTF-16LE - Sixteen-bit UCS Transformation Format, little-endian byte
     * order<br>
     * UTF-16 - Sixteen-bit UCS Transformation Format, byte order identified by
     * an optional byte-order mark<br>
     */
    public InputStream(FileInputStream stream, String charsetName) throws Exception {
        fileInputStream = stream;
        encoding = charsetName;
        fileLength = stream.getChannel().size();
    }

    /**
     * Constructs a new <code>InputStream</code> with a default encoding of UTF
     * 8
     *
     * @param stream <code>FileInputStream</code> to read from
     * @throws Exception any throwable error encountered
     */
    public InputStream(FileInputStream stream) throws Exception {
        fileInputStream = stream;
        encoding = "UTF8";
        fileLength = stream.getChannel().size();
    }

    /**
     * Reads a string from the stream
     *
     * @param length the amount of bytes to read
     * @return <code>String</code>
     * @throws IOException for reading error
     */
    public String readString(int length) throws IOException {
        return new String(readBytes(length), encoding);
    }

    /**
     * Gets the next byte
     *
     * @return <code>byte</code>
     * @throws IOException for reading error
     */
    public byte readByte() throws IOException {
        return readBytes(1)[0];
    }

    /**
     * Gets an array of bytes
     *
     * @param length the amount of bytes to read
     * @return array of <code>byte</code>
     * @throws IOException for reading error
     */
    public byte[] readBytes(int length) throws IOException {
        byte[] buffer = new byte[length];
        int ret = fileInputStream.read(buffer);
        if (ret == -1) {
            throw new IOException("End of file reached");
        }
        currentPos = currentPos + length;
        return buffer;
    }

    /**
     * reads the next character
     *
     * @return <code>char</code>
     * @throws IOException for reading error
     */
    public char readChar() throws IOException {
        return readChars(1)[0];
    }

    /**
     * reads an array of characters
     *
     * @param len number of characters to read
     * @return array of <code>char</code>
     * @throws IOException for reading error
     */
    public char[] readChars(int len) throws IOException {
        return readString(len).toCharArray();
    }

    /**
     * reads an integer
     *
     * @return <code>int</code>
     * @throws IOException for reading error
     */
    public int readInt() throws IOException {
        return readInt(0);
    }

    /**
     * reads an integer at a specified offset
     *
     * @param offset the start offset in the file to start reading
     * @return <code>int</code>
     * @throws IOException for reading error
     */
    public int readInt(int offset) throws IOException {
        byte[] buffer = new byte[4];
        fileInputStream.read(buffer, offset, 4);
        return (int) getByteArrayToInt(buffer);
    }

    /**
     * reads a <code>Short</code> at a specified offset
     *
     * @return <code>short</code>
     * @throws IOException for reading error
     */
    public short readShort() throws IOException {
        return readShort(0);
    }

    /**
     * reads a <code>Short</code> at a specified offset
     *
     * @param offset the start offset in the file to start reading
     * @return <code>short</code>
     * @throws IOException for reading error
     */
    public short readShort(int offset) throws IOException {
        byte[] buffer = new byte[2];
        fileInputStream.read(buffer, offset, 2);
        return (short) getByteArrayToInt(buffer);
    }

    /**
     * reads a <code>long</code> at a specified offset
     *
     * @return <code>long</code>
     * @throws IOException for reading error
     */
    public long readLong() throws IOException {
        return readLong(0);
    }

    /**
     * reads a <code>long</code> at a specified offset
     *
     * @param offset the start offset in the file to start reading
     * @return <code>long</code>
     * @throws IOException for reading error
     */
    public long readLong(int offset) throws IOException {
        byte[] buffer = new byte[8];
        fileInputStream.read(buffer, offset, 8);
        return (long) getByteArrayToInt(buffer);
    }

    /**
     * reads a <code>double</code> at a specified offset
     *
     * @return <code>double</code>
     * @throws IOException for reading error
     */ 
    public double readDouble() throws IOException {
        return readDouble(0);
    }

    /**
     * reads a <code>double</code> at a specified offset
     *
     * @param offset the start offset in the file to start reading
     * @return <code>double</code>
     * @throws IOException for reading error
     */
    public double readDouble(int offset) throws IOException {
        byte[] buffer = new byte[8];
        fileInputStream.read(buffer, offset, 8);
        return (double) getByteArrayToDouble(buffer);
    }

    /**
     * reads a <code>float</code> at a specified offset
     *
     * @return <code>float</code>
     * @throws IOException for reading error
     */
    public float readFloat() throws IOException {
        return readFloat(0);
    }

    /**
     * reads a <code>double</code> at a specified offset
     *
     * @param offset the start offset in the file to start reading
     * @return <code>float</code>
     * @throws IOException for reading error
     */
    public float readFloat(int offset) throws IOException {
        byte[] buffer = new byte[4];
        fileInputStream.read(buffer, offset, 4);
        return (float) getByteArrayToDouble(buffer);
    }

    /**
     * Skips a section of the file
     *
     * @param offset how far to skip
     * @throws IOException for reading error
     */
    public void skip(long offset) throws IOException {
        fileInputStream.skip(offset);
        currentPos = currentPos + offset;
    }

    private Object getByteArrayToInt(byte[] buffer) {
        if (this.endian == ENDIAN.LITTLE_ENDIAN) {
            ArrayUtils.reverse(buffer);
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        switch (buffer.length) {
            case 2:
                currentPos = getCurrentPos() + 2;
                return byteBuffer.getShort();
            case 4:
                currentPos = getCurrentPos() + 4;
                return byteBuffer.getInt();
            case 8:
                currentPos = getCurrentPos() + 8;
                return byteBuffer.getLong();
            default:
                return null;
        }
    }

    private float getByteArrayTofloat(byte[] buffer) throws NumberFormatException {
        if (this.endian == ENDIAN.LITTLE_ENDIAN) {
            ArrayUtils.reverse(buffer);
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        switch (buffer.length) {
            case 4:
                currentPos = getCurrentPos() + 4;
                return byteBuffer.getFloat();
            default:
                throw new NumberFormatException("Invalid buffer size");
        }
    }

    private double getByteArrayToDouble(byte[] buffer) throws NumberFormatException {
        if (this.endian == ENDIAN.LITTLE_ENDIAN) {
            ArrayUtils.reverse(buffer);
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        switch (buffer.length) {
            case 8:
                currentPos = getCurrentPos() + 8;
                return byteBuffer.getDouble();
            default:
                throw new NumberFormatException("Invalid buffer size");
        }
    }

    /**
     * @return the fileLength
     */
    public long getFileLength() {
        return fileLength;
    }

    /**
     * @return the currentPos
     */
    public long getCurrentPos() {
        return currentPos;
    }

    @Override
    /**
     * closes the stream
     */
    public void close() throws IOException {
        fileInputStream.close();
    }
}
