/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A simple class with generic serialize and deserialize method implementations
 * 
 * @author pankaj
 * 
 */
public class SerializationUtil {

    // deserialize to Object from given file
    /**
     * Opens a serialized object
     * 
     * @param fileName
     *            the full path to the serialized object
     * @return <code>Object</code> that is the serialized object
     * @throwsIOException an error reading the file * @throwsClassNotFoundException
     *                    class reading error
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
	FileInputStream fis = new FileInputStream(fileName);
	ObjectInputStream ois = new ObjectInputStream(fis);
	Object obj = ois.readObject();
	ois.close();
	return obj;
    }

    // serialize the given object and save it to file
    /**
     * Saves an object to a file
     * 
     * @param obj
     *            the object to be saved
     * @param fileName
     *            the full path of the file to save the information in
     * @throwsIOException an error writing the file
     */
    public static void serialize(Object obj, String fileName) throws IOException {
	FileOutputStream fos = new FileOutputStream(fileName);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(obj);

	fos.close();
    }

}
