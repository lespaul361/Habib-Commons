/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.Streams.XML;

/**
 * XML attributes are normally used to describe XML elements, or to provide
 * additional information about elements.
 *
 *
 *
 * @author Charles Hamilton
 */
public class Attribute {

    private String value;
    private String name;

    /**
     * Constructs an <code>Attribute</code> with the value and the name defined
     *
     * @param value value of the attribute
     * @param name name of the attribute
     */
    public Attribute(String value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
