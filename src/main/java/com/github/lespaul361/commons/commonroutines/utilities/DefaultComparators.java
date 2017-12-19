/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.util.Comparator;

/**
 *
 * @author Charles Hamilton
 */
public class DefaultComparators {

    /**
     * Gets a <code>String</code> <code>Comparator</code>
     *
     * @param isCaseSensative if case is used to determine sorting
     * @return a <code>String</code> <code>Comparator</code>
     */
    public static Comparator getDefaultStringComparator(boolean isCaseSensative) {
        class c implements Comparator<String> {

            private final boolean checkCase;

            public c(boolean caseSensative) {
                checkCase = caseSensative;
            }

            @Override
            public int compare(String obj1, String obj2) {
                if (obj1 == null) {
                    return -1;
                }
                if (obj2 == null) {
                    return 1;
                }
                if (!checkCase) {
                    return obj1.compareTo(obj2);
                }
                return obj1.compareToIgnoreCase(obj2);
            }

        }
        return new c(isCaseSensative);
    }

    /**
     * Gets a <code>double</code> <code>Comparator</code>
     *
     * @return a <code>double</code> <code>Comparator</code>
     */
    public static Comparator getDefaultDoubleComparator() {
        class DoubleComparator implements Comparator<Double> {

            @Override
            public int compare(Double d1, Double d2) {
                return Double.compare(d1, d2);
            }

        }
        return new DoubleComparator();
    }

    /**
     * Gets a <code>int</code> <code>Comparator</code>
     *
     * @return a <code>int</code> <code>Comparator</code>
     */
    public static Comparator getDefaultIntegerComparator() {
        class IntegerComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer i1, Integer i2) {
                return Integer.compare(i1, i2);
            }

        }
        return new IntegerComparator();
    }
}
