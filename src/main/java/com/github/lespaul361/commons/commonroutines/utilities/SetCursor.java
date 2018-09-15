/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;

/**
 * Helper class for common cursor changes
 * 
 * @author Charles Hamilton
 */
public class SetCursor {

    private final static MouseAdapter mouseAdapter = new MouseAdapter() {
    };

    /**
     * Sets the cursor to wait cursor and blocks mouse events
     * 
     * @param component
     *            component on frame to set the cursor
     */
    public static void setWaitCursor(JComponent component) {
	changeCursor(Cursor.WAIT_CURSOR, component, true);
    }

    /**
     * Sets the cursor to wait cursor and blocks mouse events
     * 
     * @param component
     *            JFrame to set the cursor
     */
    public static void setWaitCursor(JFrame component) {
	changeCursor(Cursor.WAIT_CURSOR, component.getGlassPane(), true);
    }

    /**
     * Sets the cursor to wait cursor and blocks mouse events
     * 
     * @param component
     *            JDialog to set the cursor
     */
    public static void setWaitCursor(JDialog component) {
	changeCursor(Cursor.WAIT_CURSOR, component.getGlassPane(), true);
    }

    /**
     * Sets the cursor to the default cursor and blocks mouse events
     * 
     * @param component
     *            JFrame to set the cursor
     */
    public static void setDefaultCursor(JFrame component) {
	changeCursor(Cursor.DEFAULT_CURSOR, component.getGlassPane(), false);
    }

    /**
     * Sets the cursor to the default cursor and blocks mouse events
     * 
     * @param component
     *            JDialog to set the cursor
     */
    public static void setDefaultCursor(JDialog component) {
	changeCursor(Cursor.DEFAULT_CURSOR, component.getGlassPane(), false);
    }

    /**
     * Sets the cursor to default cursor and allows mouse events
     * 
     * @param component
     *            component on frame to set the cursor
     */
    public static void setDefaultCursor(JComponent component) {
	changeCursor(Cursor.DEFAULT_CURSOR, component, false);
    }

    /**
     * Sets the cursor to decided cursor. If it is wait cursor then it blocks
     * mouse events
     * 
     * @param cursor
     *            integer value of cursor
     * @param component
     *            component on frame to set the cursor
     * 
     * 
     *            <p>
     *            DEFAULT_CURSOR = 0 <br>
     *            CROSSHAIR_CURSOR = 1 <br>
     *            TEXT_CURSOR = 2 <br>
     *            WAIT_CURSOR = 3 <br>
     *            SW_RESIZE_CURSOR = 4 <br>
     *            SE_RESIZE_CURSOR = 5 <br>
     *            NW_RESIZE_CURSOR = 6 <br>
     *            NE_RESIZE_CURSOR = 7 <br>
     *            N_RESIZE_CURSOR = 8 <br>
     *            S_RESIZE_CURSOR = 9 <br>
     *            W_RESIZE_CURSOR = 10 <br>
     *            E_RESIZE_CURSOR = 11 <br>
     *            HAND_CURSOR = 12 <br>
     *            MOVE_CURSOR = 13
     */
    public static void setCursor(int cursor, JComponent component) {
	changeCursor(cursor, component, false);
    }

    /**
     * Sets the cursor to decided cursor. If it is wait cursor or
     * stopMouseEvents is true then it blocks mouse events
     * 
     * @param cursor
     *            integer value of cursor
     * @param component
     *            component on frame to set the cursor
     * @param stopMouseEvents
     *            if true mouse events are stopped during the cursor otherwise
     *            mouse events are allowed
     * 
     *            <p>
     *            DEFAULT_CURSOR = 0 <br>
     *            CROSSHAIR_CURSOR = 1 <br>
     *            TEXT_CURSOR = 2 <br>
     *            WAIT_CURSOR = 3 <br>
     *            SW_RESIZE_CURSOR = 4 <br>
     *            SE_RESIZE_CURSOR = 5 <br>
     *            NW_RESIZE_CURSOR = 6 <br>
     *            NE_RESIZE_CURSOR = 7 <br>
     *            N_RESIZE_CURSOR = 8 <br>
     *            S_RESIZE_CURSOR = 9 <br>
     *            W_RESIZE_CURSOR = 10 <br>
     *            E_RESIZE_CURSOR = 11 <br>
     *            HAND_CURSOR = 12 <br>
     *            MOVE_CURSOR = 13
     */
    public static void setCursor(int cursor, JComponent component, boolean stopMouseEvents) {
	changeCursor(cursor, component, stopMouseEvents);
    }

    private static void changeCursor(int cursor, JComponent component, boolean stopMouse) {
	RootPaneContainer rcp = ((RootPaneContainer) component.getTopLevelAncestor());
	changeCursor(cursor, rcp, stopMouse);
    }

    private static void changeCursor(int cursor, RootPaneContainer rcp, boolean stopMouse) {
	Component glassPane = rcp.getGlassPane();
	changeCursor(cursor, glassPane, stopMouse);
    }

    private static void changeCursor(int cursor, Component glassPane, boolean stopMouse) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                if ((cursor == Cursor.WAIT_CURSOR) || stopMouse) {
                    glassPane.addMouseListener(mouseAdapter);
                } else {
                    glassPane.removeMouseListener(mouseAdapter);
                }
                glassPane.setVisible((cursor == Cursor.WAIT_CURSOR) || stopMouse);
                glassPane.setCursor(Cursor.getPredefinedCursor(cursor));
            }

        };
        if (!SwingUtilities.isEventDispatchThread()) {
            try {
                SwingUtilities.invokeLater(r);
            } catch (Exception e) {
            }
        } else {
            r.run();
        }
    }
}
