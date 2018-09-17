/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Helper class for simple message routines
 *
 * @author Charles Hamilton
 */
public class MessageRoutines {

    /**
     * Shows a message box with 1 button that says OK
     *
     * @param message the message to show the user
     * @param title the title of the message box
     * @param frame the parent frame
     */
    public static void showOkOnly(String message, String title, JFrame frame) {
        Object[] options = {"   OK   "};
        int n = JOptionPane.showOptionDialog(frame, message, title, JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    /**
     * Shows a message box with 1 button that says OK
     *
     * @param message the message to show the user
     * @param title the title of the message box
     * @param messageType determines the icon to be shown in the message box
     * @param frame the parent frame
     */
    public static void showOkOnly(String message, String title, int messageType, JFrame frame) {
        Object[] options = {"   OK   "};
        int n = JOptionPane.showOptionDialog(frame, message, title, JOptionPane.PLAIN_MESSAGE, messageType, null,
                options, options[0]);
    }
}
