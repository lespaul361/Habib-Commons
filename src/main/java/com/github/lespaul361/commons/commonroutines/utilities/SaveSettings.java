/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.util.prefs.Preferences;

/**
 * Helper class to store and retrieve saved settings
 *
 * @author David
 */
public class SaveSettings {

    /**
     * Saves a setting
     *
     * @param rootNodeName path of the setting
     * @param nodeName name of the setting
     * @param nodeValue value of the setting
     */
    public static void saveSetting(String rootNodeName, String nodeName, String nodeValue) {
        Preferences prefs = Preferences.userRoot().node(rootNodeName);
        prefs.put(nodeName, nodeValue);
    }

    /**
     * Gets a saved setting
     *
     * @param rootNodeName the path of the setting
     * @param nodeName the name of the setting
     * @return the value of the setting
     */
    public static String getSetting(String rootNodeName, String nodeName) {
        Preferences prefs = Preferences.userRoot().node(rootNodeName);
        String ret = prefs.get(nodeName, "");
        return ret;
    }
}
