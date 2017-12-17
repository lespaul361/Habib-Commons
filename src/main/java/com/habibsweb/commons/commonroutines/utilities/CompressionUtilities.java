/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habibsweb.commonroutines.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 *
 * @author lespa
 */
public class CompressionUtilities {

    public static byte[] deflateList(Object list) {
        return deflateList(list, Deflater.DEFAULT_COMPRESSION);
    }

    public static byte[] deflateList(Object list, int level) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(list);
            byte[] data = bos.toByteArray();
            Deflater deflater = new Deflater(level);
            deflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            deflater.finish();
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer); // returns the generated code... index  
                outputStream.write(buffer, 0, count);
            }
            byte[] output = outputStream.toByteArray();
            outputStream.close();
            oos.close();
            bos.close();
            //Object test=inflateList(output);
            return output;
        } catch (Exception e) {
        }
        return null;
    }

    public static Object inflateList(byte[] data) {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
                if (count == 0) {
                    break;
                }
            }
            outputStream.close();
            byte[] output = outputStream.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(output);
            ObjectInputStream objectInputStream = new ObjectInputStream(bis);
            Object ret = objectInputStream.readObject();
            bis.close();
            objectInputStream.close();
            outputStream.close();
            return ret;

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public static byte [] inflateStream(byte [] data){
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
                if (count == 0) {
                    break;
                }
            }
            outputStream.close();
            byte[] output = outputStream.toByteArray();
            return output;
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] deflateStream(byte[] stream, int level) {
        Deflater deflater = new Deflater(level);
        deflater.setInput(stream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(stream.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer); // returns the generated code... index  
            outputStream.write(buffer, 0, count);
        }
        byte[] output = outputStream.toByteArray();
        return output;
    }

    public static byte[] deflateStream(byte[] stream) {
        return deflateStream(stream, Deflater.DEFAULT_COMPRESSION);
    }
}
