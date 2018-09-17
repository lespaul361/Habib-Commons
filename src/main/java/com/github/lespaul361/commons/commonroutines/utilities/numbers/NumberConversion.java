/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.numbers;

import java.nio.ByteBuffer;

/**
 * Helper class for number conversion
 *
 * @author Charles Hamilton
 */
public class NumberConversion {

    /**
     * Converts signed integer to long
     *
     * @param byteData bytes storing the integer value
     * @return <code>long</code>
     */
    public static long convertSignedIntToLong(byte[] byteData) {
        long result = 0x00FF & byteData[0];
        result <<= 8;
        result += 0x00FF & byteData[1];
        result <<= 8;
        result += 0x00FF & byteData[2];
        result <<= 8;
        result += 0x00FF & byteData[3];

        return result;
    }

    /**
     * Converts signed integer to long
     *
     * @param value the integer to convert
     * @return <code>long</code>
     */
    public static long convertSignedIntToLong(int value) {
        byte[] byteData = ByteBuffer.allocate(4).putInt(1695609641).array();
        long result = 0x00FF & byteData[0];
        result <<= 8;
        result += 0x00FF & byteData[1];
        result <<= 8;
        result += 0x00FF & byteData[2];
        result <<= 8;
        result += 0x00FF & byteData[3];

        return result;
    }

    /**
     * Converts signed byte to integer
     *
     * @param byteData the byte to convert
     * @return <code>int</code>
     */
    public static int convertSignedByteToInt(byte byteData) {
        int result = 0xFF & byteData;
        return result;
    }
}
