/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.io.Serializable;

/**
 *
 * @author David Hamilton
 * @param <T> the data type this class uses
 */
public class NameValuePair<T> implements Serializable {

    private String name = "";
    private T value = null;

    /**
     * Constructs an empty <code>NameValuePair</code>
     */
    public NameValuePair() {
        this(null, null);
    }

    /**
     * Constructs a <code>NameValuePair</code> with a name
     *
     * @param name the name of the pair
     */
    public NameValuePair(String name) {
        this(name, null);
    }

    /**
     * Constructs a <code>NameValuePair</code> with name and value
     *
     * @param name the name of the pair
     * @param value the value of the pair
     */
    public NameValuePair(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the <code>NameValuePair</code>
     *
     * @return the name the name of the <code>NameValuePair</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the <code>NameValuePair</code>
     *
     * @param name the name of the <code>NameValuePair</code>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of the <code>NameValuePair</code>
     *
     * @return the value the value of the <code>NameValuePair</code>
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value of the <code>NameValuePair</code>
     *
     * @param value the value of the <code>NameValuePair</code>
     */
    public void setValue(T value) {
        this.value = value;
    }

}
