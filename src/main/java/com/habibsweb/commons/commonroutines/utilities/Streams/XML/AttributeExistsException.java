/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habibsweb.commons.commonroutines.utilities.Streams.XML;

/**
 * An <code>Exception</code> Fired when an <code> Attribute</code> already
 * exists
 *
 * @author Charles Hamilton
 */
public class AttributeExistsException extends Exception {

    private final String attributeName;

    /**
     * Constructs a new <code>AttributeExistsException</code> 
     * @param attributeName the name of the existing attribute
     */
    public AttributeExistsException(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    /**
     * Returns the detail message string of this <code>Exception</code>.
     */
    public String getMessage() {
        return attributeName + " already exists";
    }

}
