/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.Streams.XML;

/**
 * Exception thrown when the XML file header is not as expected or incorrect
 *
 * @author David Hamilton
 */
public class InvalidHeaderException extends Exception {

    /**
     * Constructs a new <code>Exception thrown when the XML file header is not as expected or incorrect</code>
     *
     * @param string the message to pass to the user
     */
    public InvalidHeaderException(String string) {
        super(string);
    }
}
