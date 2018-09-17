/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A helper class for getting images
 *
 * @author Charles Hamilton
 */
public class CreateImageFromResource {

    /**
     *
     * @param path to resource path
     * @param description of the resource
     * @param resourceClass class containing the resource path
     * @return <code>Image</code> in the resource
     */
    public static Image getImage(String path, String description, Class resourceClass) {
        return createImage(path, description, resourceClass);
    }

    /**
     * Gets an image from a resource path
     *
     * @param path String to resource path
     * @param resourceClass class containing the resource path
     * @return <code>Image</code> in the resource
     */
    public static Image getImage(String path, Class resourceClass) {
        return createImage(path, "", resourceClass);
    }

    /**
     * Gets an image from a resource path
     *
     * @param path String to resource path
     * @param description of the resource
     * @param resourceClass class containing the resource path
     * @return <code>Image</code> in the resource
     */
    protected static Image createImage(String path, String description, Class resourceClass) {
        URL imageURL = resourceClass.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    /**
     * Gets the icon from the file extension
     *
     * @param extension the file extension
     * @return <code>Icon</code> from the extension
     */
    public static Icon getIconFromFileExtension(String extension) {
        int pos = extension.lastIndexOf(".");
        Icon icon = null;
        if (extension.trim().equalsIgnoreCase("")) {
            return null;
        }
        if (pos > -1) {
            extension = extension.substring(pos + 1);
        }
        File fl = new File("tmp." + extension);

        try {
            fl.createNewFile();
            icon = javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(fl);

        } catch (Exception ex) {
            Logger.getLogger(CreateImageFromResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

            } catch (Exception e) {
                fl.delete();
            }
        }
        return icon;
    }
}
