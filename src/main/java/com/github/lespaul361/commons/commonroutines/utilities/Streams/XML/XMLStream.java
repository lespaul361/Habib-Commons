/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.Streams.XML;

import com.github.lespaul361.commons.commonroutines.utilities.Streams.WriterOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

/**
 * Stream for reading and writing XML documents
 *
 * @author Charles Hamilton
 */
public class XMLStream {

    RootNode root;
    protected final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"{0}\" standalone=\"{1}\"?>";
    protected float version = Float.parseFloat("1.0");
    private boolean isChanged = false;
//completedString = completedString.replaceAll("\n", System.getProperty("line.separator"));

    /**
     * Allowed encoding for XML documents. UTF 8 is the default. Not all
     * encoding and decoding are allowed in Java. Those that are not will be
     * marked as unsupported at this time. UTF-16 will be used in place of
     * those.
     */
    public enum StringEncodings {

        /**
         * UTF-8 is a character encoding capable of encoding all possible
         * characters, or code points, defined by Unicode and originally
         * designed by Ken Thompson and Rob Pike. The encoding is
         * variable-length and uses 8-bit code units.
         */
        UTF_8,
        /**
         * UTF-16 (16-bit Unicode Transformation Format) is a character encoding
         * capable of encoding all 1,112,064 possible characters in Unicode. The
         * encoding is variable-length, as code points are encoded with one or
         * two 16-bit code units.
         */
        UTF_16,
        /**
         * <p>
         * The Universal Coded Character Set (UCS), is a standard set of
         * characters defined by the International Standard ISO/IEC 10646,
         * Information technology -- Universal Coded Character Set (UCS) (plus
         * amendments to that standard), which is the basis of many character
         * encodings. The UCS contains over 128,000 abstract characters, each
         * identified by an unambiguous name and an integer number called its
         * code point.</p>
         * <p>
         * Unsupported at this time</p>
         */
        ISO_10646_UCS_2,
        /**
         * <p>
         * The Universal Coded Character Set (UCS), is a standard set of
         * characters defined by the International Standard ISO/IEC 10646,
         * Information technology -- Universal Coded Character Set (UCS) (plus
         * amendments to that standard), which is the basis of many character
         * encodings. The UCS contains over 128,000 abstract characters, each
         * identified by an unambiguous name and an integer number called its
         * code point.</p>
         * <p>
         * Unsupported at this time</p>
         */
        ISO_10646_UCS_4,
        /**
         * ISO 8859-1 encodes what it refers to as "Latin alphabet no. 1,"
         * consisting of 191 characters from the Latin script. This
         * character-encoding scheme is used throughout the Americas, Western
         * Europe, Oceania, and much of Africa. It is also commonly used in most
         * standard romanizations of East-Asian languages.
         */
        ISO_8859_1,
        /**
         * ISO/IEC 8859-2:1999, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 2: Latin alphabet No. 2, is part of the
         * ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1987. It is informally referred to as
         * "Latin-2".
         */
        ISO_8859_2,
        /**
         * ISO/IEC 8859-3:1999, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 3: Latin alphabet No. 3, is part of the
         * ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1988. It is informally referred to as
         * Latin-3 or South European.
         */
        ISO_8859_3,
        /**
         * ISO/IEC 8859-4:1998, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 4: Latin alphabet No. 4, is part of the
         * ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1988. It is informally referred to as
         * Latin-4 or North European.
         */
        ISO_8859_4,
        /**
         * ISO/IEC 8859-5:1999, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 5: Latin/Cyrillic alphabet, is part of
         * the ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1988. It is informally referred to as
         * Latin/Cyrillic. It was designed to cover languages using a Cyrillic
         * alphabet such as Bulgarian, Belarusian, Russian, Serbian and
         * Macedonian but was never widely used.
         */
        ISO_8859_5,
        /**
         * ISO/IEC 8859-6:1999, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 6: Latin/Arabic alphabet, is part of
         * the ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1987. It is informally referred to as
         * Latin/Arabic. It was designed to cover languages using the Arabic
         * alphabet (though it does not include the extra letters needed to
         * write most Arabic-script languages other than Arabic itself, such as
         * Persian, Urdu, etc.).
         */
        ISO_8859_6,
        /**
         * ISO/IEC 8859-7:2003, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 7: Latin/Greek alphabet, is part of the
         * ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1987. It is informally referred to as
         * Latin/Greek. It was designed to cover the modern Greek language.
         */
        ISO_8859_7,
        /**
         * ISO/IEC 8859-8:1999, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 8: Latin/Hebrew alphabet, is part of
         * the ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1987. It is informally referred to as
         * Latin/Hebrew. ISO/IEC 8859-8 covers all the Hebrew letters, but no
         * Hebrew vowel signs.
         */
        ISO_8859_8,
        /**
         * ISO/IEC 8859-9:1999, Information technology -- 8-bit single-byte coded
         * graphic character sets -- Part 9: Latin alphabet No. 5, is part of the
         * ISO/IEC 8859 series of ASCII-based standard character encodings,
         * first edition published in 1989. It is informally referred to as
         * Latin-5 or Turkish. It was designed to cover the Turkish language,
         * designed as being of more use than the ISO/IEC 8859-3 encoding. It is
         * identical to ISO/IEC 8859-1 except for these six replacements of
         * Icelandic characters with characters unique to the Turkish alphabet
         */
        ISO_8859_9,
        /**
         * The text with "ISO-2022-JP-2" starts in ASCII [ASCII], and switches
         * to other character sets of ISO 2022 [ISO2022] through limited
         * combinations of escape sequences. All the characters are encoded with
         * 7 bits only.
         */
        ISO_2022_JP,
        /**
         * Shift JIS (Shift Japanese Industrial Standards, also SJIS, MIME name
         * Shift_JIS) is a character encoding for the Japanese language,
         * originally developed by a Japanese company called ASCII Corporation
         * in conjunction with Microsoft and standardized as JIS X 0208 Appendix
         * 1. 1.1% of all web pages use Shift JIS in January 2016.
         */
        Shift_JIS,
        /**
         * Extended Unix Code (EUC) is a multibyte character encoding system
         * used primarily for Japanese, Korean, and simplified Chinese.
         */
        EUC_JP;

    }
    private StringEncodings encoding = StringEncodings.UTF_8;
    private boolean isStandAlone = true;
    private StringReader bsr;

    /**
     * @return the isStandAlone
     */
    public boolean getIsStandAlone() {
        return isStandAlone;
    }

    /**
     * @param isStandAlone the isStandAlone to set
     */
    public void setIsStandAlone(boolean isStandAlone) {
        this.isStandAlone = isStandAlone;
    }

    /**
     * @return the encoding
     */
    public StringEncodings getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(StringEncodings encoding) {
        this.encoding = encoding;
    }

    /**
     * Constructs a new <code>XMLStream</code> with a root <code>Node</code>
     * name.
     *
     * @param rootNodeName name of the node. "" can be used.
     */
    public XMLStream(String rootNodeName) {
        RootNode node = new RootNode();
        node.setName(rootNodeName);
        root = node;
    }

    /**
     * Constructs a new <code>XMLStream</code> with a root <code>Node</code>
     * name and string encoding type.
     *
     * @param rootNodeName name of the node. "" can be used.
     * @param encoding the encoding to set
     */
    public XMLStream(String rootNodeName, StringEncodings encoding) {
        this(rootNodeName);
        this.encoding = encoding;
    }

    /**
     * Constructs a new <code>XMLStream</code> with a root <code>Node</code>
     * name and an <code>Attribute</code>
     *
     * @param rootNodeName name of the node. "" can be used.
     * @param attribute the <code>attribute</code> for this <code>Node</code>
     */
    public XMLStream(String rootNodeName, com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute attribute) {
        this(rootNodeName);
        root.addAttribute(attribute);
    }

    /**
     * Constructs a new <code>XMLStream</code> with a root <code>Node</code>
     * name and an array of <code>Attribute</code>
     *
     * @param rootNodeName name of the node. "" can be used.
     * @param attributes an array of <code>attribute</code> for this
     * <code>Node</code>
     */
    public XMLStream(String rootNodeName, com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute[] attributes) {
        this(rootNodeName);
        for (com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute att : attributes) {
            root.addAttribute(att);
        }
    }

    /**
     * Constructs a new <code>XMLStream</code> from a XML File
     *
     * @param file the <code>File</code> to read
     * @throwsIOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.     * @throwsInvalidHeaderException Exception thrown when the XML file header is not as expected or incorrect      */
    public XMLStream(File file) throws IOException, InvalidHeaderException {
        this(new FileInputStream(file));
    }

    /**
     * Constructs a new <code>XMLStream</code> from as <code>InputStream</code>
     *
     * @param in an <code>InputStream</code>
     * @throwsIOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.     * @throwsInvalidHeaderException Exception thrown when the XML file header is not as expected or incorrect     * @throws NullPointerException
     * @see InputStream
     *
     */


    public XMLStream(InputStream in) throws IOException, InvalidHeaderException, NullPointerException {
        final int kb = 1024;
        byte[] buffer = new byte[kb];
        ByteArrayOutputStream baos = new ByteArrayOutputStream(kb * kb);//set to 1 megabyte
        int len;
        while ((len = in.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        buffer = baos.toByteArray();
        Charset charset = getCharset(buffer);
        if (charset == null) {
            throw new NullPointerException("Charset cannot be found");
        }
        read(new ByteArrayInputStream(buffer), charset);
    }

    public XMLStream(InputStream in, StringEncodings enc) throws IOException, InvalidHeaderException {
        Charset charset = getCharsetFromString(enc.toString());
        read(in, charset);
    }

    private Charset getCharset(byte[] fileData) {
        SortedMap<String, Charset> charsets = java.nio.charset.Charset.availableCharsets();
        Iterator<Entry<String, Charset>> iterator = charsets.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Charset> entry = iterator.next();
            System.out.println(entry.getKey());
            ByteArrayInputStream bais = new ByteArrayInputStream(fileData);
            BufferedReader br = new BufferedReader(new InputStreamReader(bais, entry.getValue()));
            try {
                String tmp = br.readLine();
                if (tmp.toLowerCase().contains("encoding")) {
                    tmp = tmp.substring(tmp.indexOf("encoding"));
                    tmp = tmp.substring(tmp.indexOf("\"") + 1);
                    tmp = tmp.substring(0, tmp.indexOf("\""));
                    return java.nio.charset.Charset.forName(tmp);
                }
            } catch (Exception e) {
            }

        }
        return null;
    }

    private void read(InputStream in, Charset charset) throws IOException, InvalidHeaderException {
        BufferedReader reader = null;
        InputStreamReader isr = new InputStreamReader(in, charset);
        reader = new BufferedReader(isr);
        boolean hasHeader = setUpHeaderFromBufferedReader(reader, charset);
        if (!hasHeader) {
            throw new InvalidHeaderException("Header is corrupt");
        }

    }

    private void read(BufferedReader reader) {
        String tmp = "";
        String currentLine = "";
        String lastLine = "";

        int pos1 = -1;
        int pos2 = -1;
        reader = new BufferedReader(bsr);
        Node parent = null;
        Node currentNode = null;
        String nodeName = "";
        Attribute[] attributes = null;

        try {
            if (true) {//hasHeader) {
                reader.readLine();
            }
            for (currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
                currentLine = currentLine.trim();
                if (!lastLine.equalsIgnoreCase("")) {
                    lastLine = lastLine + currentLine + "/n";
                    currentLine = lastLine;
                }
                if (!currentLine.equalsIgnoreCase("")) {
                    if ((currentLine.length() > 1) && (currentLine.substring(0, 2).equalsIgnoreCase("<\\"))) {
                        //end of node
                    } else if ((currentLine.length() > 3) && (currentLine.substring(0, 4).equalsIgnoreCase("<!cd"))) {
                        //CDATA section
                    } else if ((currentLine.length() > 1) && (currentLine.substring(0, 2).equalsIgnoreCase("<?"))) {
                        // processing instruction
                    } else {
                        //new node
                        if (isHeaderTag(currentLine)) {
                            if (this.root == null) {
                                this.root = new RootNode();
                                this.root.setName(getTagName(currentLine));
                                if (hasAttributes(currentLine)) {
                                    this.root.addAttributes(readAttributesFromString(getAttributeLine(currentLine)));
                                }
                            } else {
                                if (parent == null) {
                                    currentNode = this.root.createNode(getTagName(currentLine));
                                } else {
                                    currentNode = parent.createNode(getTagName(currentLine));
                                }

                                if (hasAttributes(currentLine)) {
                                    currentNode.addAttributes(readAttributesFromString(getAttributeLine(currentLine)));
                                }
                                currentNode.setValue(getTagValue(currentLine));
                                if (parent == null) {
                                    parent = currentNode;
                                    parent.parentNode = root;
                                    currentNode = null;
                                } else {
                                    parent = currentNode;
                                }
                            }
                        } else if (isCompleteTag(currentLine, getTagName(currentLine))) {
                            currentNode = parent.createNode(getTagName(currentLine));
                            if (hasAttributes(currentLine)) {
                                currentNode.addAttributes(readAttributesFromString(getAttributeLine(currentLine)));
                            }
                            currentNode.setValue(getTagValue(currentLine));
                            lastLine = "";
                        } else if (isEndTag(currentLine, parent.getName())) {
                            if (parent != null) {
                                currentNode = null;
                                if (!parent.getName().equalsIgnoreCase(this.root.getName())) {
                                    parent = parent.parentNode;
                                }
                            }
                        } else {
                            //continued tag
                            if (lastLine.equalsIgnoreCase("")) {
                                lastLine = currentLine + "\n";
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private Charset getCharsetFromString(String charsetName) {
        Charset ret = null;
        try {
            ret = java.nio.charset.Charset.forName(charsetName);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            ret = null;
        }
        return ret;
    }

    private boolean isHeaderTag(String line) {
        if (line.contains("<") && line.contains(">")) {
            if (!line.contains("/>") && !line.contains("</")) {
                if (line.indexOf(">") + 1 == line.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isEndTag(String line, String tagName) {
        if (tagName.equalsIgnoreCase("")) {
            return false;
        }
        return (line.equalsIgnoreCase("</" + tagName + ">"));
    }

    private boolean isCompleteTag(String line, String tagName) {
        if (line.contains("<" + tagName)) {
            if (line.contains("</" + tagName)) {
                return true;
            } else if (line.contains("/>")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAttributes(String line) {
        if (line.contains("=")) {
            return true;
        }
        return false;
    }

    private String getTagValue(String line) {
        int pos1;
        int pos2;

        pos1 = line.indexOf(">") + 1;
        pos2 = line.indexOf("<", pos1);
        if (pos2 < pos1) {
            return "";
        }
        String ret = line.substring(pos1, pos2);

        return ret;
    }

    private String getTagName(String line) {
        String line1 = line.substring(0, line.indexOf(">") + 1);
        int pos1 = line1.indexOf(" ");
        int pos2 = line1.indexOf(">");
        String nodeName = "";

        if (pos1 == -1) {
            nodeName = line1.substring(1, pos2);
        } else if (pos1 > pos2) {
            pos1 = line1.indexOf("<");
        } else {
            nodeName = line1.substring(1, pos1);
        }

        nodeName = nodeName.trim();

        return nodeName;
    }

    private String getAttributeLine(String line) {
        int pos1 = line.indexOf(" ");
        int pos2 = line.indexOf(">");
        String ret = line.substring(pos1, pos2);
        return ret;

    }

    private boolean setUpHeaderFromBufferedReader(BufferedReader reader, Charset charset) throws InvalidHeaderException {
        String tmp = "";
        String currentLine = "";
        boolean hasHeader = false;
        StringWriter sw = new StringWriter();
        BufferedWriter writer = new BufferedWriter(sw);
        try {
            currentLine = reader.readLine();
            writer.write(currentLine);
            writer.newLine();
            currentLine = currentLine.trim();
            if (currentLine.isEmpty()) {
                throw new InvalidHeaderException("First line can not be empty");
            }
            if (!currentLine.substring(0, 1).equalsIgnoreCase("<")) {
                throw new InvalidHeaderException("Invalid character in beginning of XML header");
            }
            if ((currentLine.length() > 1) && (currentLine.substring(0, 5).equalsIgnoreCase("<?xml"))) {
                //is header
                while (!currentLine.contains(">")) {
                    currentLine = currentLine.concat(" " + reader.readLine());
                }
                hasHeader = true;
                tmp = readAttributeValueFromString(currentLine, "version");
                if (!tmp.equalsIgnoreCase("")) {
                    this.version = Float.parseFloat(tmp);
                }

                tmp = readAttributeValueFromString(currentLine, "standalone");
                if (!tmp.equalsIgnoreCase("")) {
                    if (tmp.equalsIgnoreCase("no")) {
                        this.isStandAlone = false;
                    } else {
                        this.isStandAlone = true;
                    }
                }
                tmp = readAttributeValueFromString(currentLine, "encoding");
                if (!tmp.equalsIgnoreCase("")) {
                    try {
                        StringEncodings encoding;
                        encoding = StringEncodings.valueOf(tmp.toUpperCase().replace("-", "_"));
                        this.encoding = encoding;
                    } catch (Exception e) {
                        this.encoding = StringEncodings.UTF_8;
                    }
                }

            } else {
                this.isStandAlone = true;
                this.version = (float) 1.0;
                this.encoding = StringEncodings.UTF_8;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hasHeader;
    }

    private Attribute[] readAttributesFromString(String line) {
        String[] nameValues = line.split(" ");
        List<Attribute> ret = new ArrayList<>();
        String name = "";
        String value = "";
        String[] splitPair;
        for (String curPair : nameValues) {
            if (curPair.contains("=")) {
                splitPair = curPair.split("=");
                name = splitPair[0];
                value = splitPair[1];
                value = value.replace("\"", "");
                ret.add(new Attribute(value, name));
            }
        }
        return ret.toArray(new Attribute[ret.size()]);
    }

    private String readAttributeValueFromString(String line, String attributeName) {
        String tmp = "";
        int pos = line.toLowerCase().indexOf(attributeName);
        if (pos > -1) {
            pos = line.toLowerCase().indexOf("=", pos);
            tmp = line.substring(pos + 1, line.indexOf(("\""), pos + 2));
            tmp = tmp.trim();
            tmp = tmp.replace("\"", "");
        }
        return tmp;
    }

    /**
     * Writes the XML <code>RootNode</code> to the <code>Writer</code>
     *
     * @param writer the <code>Writer</code> to write the node to
     * @param isStandAlone sets if the XML document stand alone
     */
    public void save(Writer writer, boolean isStandAlone) {
        save(writer, encoding, isStandAlone);
    }

    /**
     * Writes the XML <code>RootNode</code> to the <code>Writer</code>
     *
     * @param writer the <code>Writer</code> to write the node to
     * @param encoding the encoding to use when writing the XML document
     * @param isStandAlone sets if the XML document stand alone
     */
    public void save(Writer writer, StringEncodings encoding, boolean isStandAlone) {
        this.isStandAlone = isStandAlone;
        save(writer, encoding);
    }

    /**
     * Writes the XML <code>RootNode</code> to the <code>Writer</code>
     *
     * @param writer the <code>Writer</code> to write the node to
     * @param encoding the encoding to use when writing the XML document
     */
    public void save(Writer writer, StringEncodings encoding) {
        this.encoding = encoding;
        save(writer);
    }

    /**
     * Writes the XML <code>RootNode</code> to the <code>Writer</code>
     *
     * @param writer the <code>Writer</code> to write the node to
     */
    public void save(Writer writer) {
        String newLine = System.getProperty("line.separator");
        try (BufferedWriter bw = getBufferedWriter(writer)) {
            bw.write(getHeaderLine());
            bw.write(newLine);
            bw.write(newLine);
            if (!this.root.getName().equalsIgnoreCase("")) {
                String nodes = getNodeToString(this.root, 0);
                nodes = nodes.replace("\n", newLine);
                bw.write(nodes);
            } else {
                for (Node curNode : this.root.getChildrenNodes()) {
                    String nodes = getNodeToString(curNode, 0);
                    nodes = nodes.replace("\n", newLine);
                    bw.write(nodes);
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the XML <code>RootNode</code> to the specified file path
     *
     * @param filePath the full path to of the file to save the XML Document
     * @param isStandAlone sets if the XML document stand alone
     */
    public void save(String filePath, boolean isStandAlone) {
        save(filePath, encoding, isStandAlone);
    }

    /**
     * Writes the XML <code>RootNode</code> to the specified file path
     *
     * @param filePath the full path to of the file to save the XML Document
     * @param encoding the encoding to use when writing the XML document
     * @param isStandAlone sets if the XML document stand alone
     */
    public void save(String filePath, StringEncodings encoding, boolean isStandAlone) {
        this.isStandAlone = isStandAlone;
        save(filePath, encoding);
    }

    /**
     * Writes the XML <code>RootNode</code> to the specified file path
     *
     * @param filePath the full path to of the file to save the XML Document
     * @param encoding the encoding to use when writing the XML document
     */
    public void save(String filePath, StringEncodings encoding) {
        this.encoding = encoding;
        save(filePath);
    }

    /**
     * Writes the XML <code>RootNode</code> to the specified file path
     *
     * @param filePath the full path to of the file to save the XML Document
     */
    public void save(String filePath) {
        String newLine = System.getProperty("line.separator");
        try (BufferedWriter bw = getBufferedWriter(filePath)) {
            bw.write(getHeaderLine());
            bw.write(newLine);
            bw.write(newLine);
            if (!this.root.getName().equalsIgnoreCase("")) {
                String nodes = getNodeToString(this.root, 0);
                nodes = nodes.replace("\n", newLine);
                bw.write(nodes);
            } else {
                for (Node curNode : this.root.getChildrenNodes()) {
                    String nodes = getNodeToString(curNode, 0);
                    nodes = nodes.replace("\n", newLine);
                    bw.write(nodes);
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private BufferedWriter getBufferedWriter(String filePath) throws Exception {
        String encodingString = this.encoding.name();
        encodingString = encodingString.replace("_", "-");

        FileOutputStream fo = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            fo = new FileOutputStream(filePath);
            osw = new OutputStreamWriter(fo, encodingString);
            bw = new BufferedWriter(osw);
        } catch (Exception e) {
            fo = new FileOutputStream(filePath);
            osw = new OutputStreamWriter(fo, "UTF-8");
            bw = new BufferedWriter(osw);
        }
        return bw;
    }

    private BufferedWriter getBufferedWriter(Writer writer) throws Exception {
        String encodingString = this.encoding.name();
        encodingString = encodingString.replace("_", "-");
        WriterOutputStream wos = null;
        try {
            wos = new WriterOutputStream(writer, Charset.defaultCharset());// encodingString);
        } catch (Exception e) {
            try {
                wos = new WriterOutputStream(writer, "UTF_8");
            } catch (Exception ex) {
                throw ex;
            }
        }

        if (wos != null) {
            try {
                BufferedWriter out = new BufferedWriter(writer);
                return out;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
        return null;
    }

    private String getTabString(int tabCount) {
        if (tabCount == 0) {
            return "";
        }
        String ret = "";

        for (int i = 0; i < tabCount; i++) {
            ret = ret + "\t";
        }

        return ret;
    }

    private String getNodeToString(Node node, Integer tabLevel) {
        int originalTabLevel = tabLevel;
        int nextNodeTabLevel = tabLevel + 1;
        StringBuilder ret = new StringBuilder();
        String originalTabString = getTabString(originalTabLevel);
        String nextNodeTabString = getTabString(nextNodeTabLevel);
        ret.append(originalTabString + "<");
        ret.append(node.getName());

        if (node.getAttributes().size() > 0) {
            ret.append(" ");
            for (com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute att : node.getAttributes()) {
                ret.append(att.getName() + "=" + "\"" + att.getValue() + "\"");
                ret.append(" ");
            }
            ret = new StringBuilder(ret.toString().substring(0, ret.length() - 1));
        }
        ret.append(">");
        try {
            if (!node.getValue().equalsIgnoreCase("")) {
                String value = node.getValue();
                value = value.replace("\n", "\n" + originalTabString);
                ret.append(value);
                ret.append("</" + node.getName() + ">");
                ret.append("\n");
            } else {
                if (node.getChildrenNodes().size() > 0) {
                    ret.append("\n");
                    for (int curNode = 0; curNode < node.getChildrenNodes().size(); curNode++) {
                        ret.append(getNodeToString(node.getChildrenNodes().get(curNode), nextNodeTabLevel));
                        String tmp = ret.toString().substring(ret.toString().length() - 1, ret.toString().length());
                        if (!tmp.equalsIgnoreCase("\n")) {
                            ret.append("\n");
                        }
                    }
                }
                ret.append(originalTabString + "</" + node.getName() + ">");
                ret.append("\n");
            }
        } catch (Exception e) {
        }

        return ret.toString();
    }

    private String nodeHeadToString(Node node) {
        StringBuilder ret = new StringBuilder();
        ret.append("<");
        ret.append(node.getName());
        if (node.getAttributes().size() > 0) {
            ret.append(" ");
            for (com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute att : node.getAttributes()) {
                ret.append(att.getName() + "=" + att.getValue());
                ret.append(" ");
            }
            ret = new StringBuilder(ret.toString().substring(0, ret.length() - 1));
        }
        ret.append(">");
        ret.append("\n");
        return ret.toString();
    }

    private String nodeEndToString(Node node) {
        return "</" + node.getName() + ">";
    }

    private String getHeaderLine() {

        String encodingString = this.encoding.toString();
        encodingString = encodingString.replace("_", "-");
        String standAloneString = "";
        if (isStandAlone) {
            standAloneString = "yes";
        } else {
            standAloneString = "no";
        }

        return MessageFormat.format(this.XML_HEADER, encodingString, standAloneString);

    }

    private boolean nodeHasValue(Node node) {
        return node.getValue().equalsIgnoreCase("");
    }

    private boolean nodeHasChildren(Node node) {
        return node.getChildrenNodes().size() > 0;
    }

    /**
     * Gets the <code>RootNode</code>
     *
     * @return <code>RootNode</code> of the XML Document
     */
    public RootNode getRootNode() {
        return this.root;
    }

    private String getEncodingToJavaEncodingString(StringEncodings enc) {
        String tmp = "";
        switch (enc) {
            //low marks to high ones
            case UTF_8:
            case UTF_16:
            case ISO_8859_1:
                tmp = enc.name();
                tmp.replace("_", "-");
                return tmp;
            //loose first score
            case ISO_8859_2:
            case ISO_8859_3:
            case ISO_8859_4:
            case ISO_8859_5:
            case ISO_8859_6:
            case ISO_8859_8:
                String[] parts = enc.name().split("_");
                tmp = parts[0];
                tmp += parts[1];
                tmp += "_" + parts[2];
                return tmp;

            //unsupported so return UTF-16
            case ISO_10646_UCS_2:
            case ISO_10646_UCS_4:
            case ISO_8859_7:
            case ISO_8859_9:
                return "UTF-16";
            //No scores
            case ISO_2022_JP:
            //other
            case Shift_JIS:
                return "SJIS";
            case EUC_JP:
                return "EUC_JP";

        }
        return null;
    }
}
