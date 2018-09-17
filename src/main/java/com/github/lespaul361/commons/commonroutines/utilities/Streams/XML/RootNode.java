/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.Streams.XML;

/**
 * The <code>RootNode</code> or top level <code>Node</code>
 *
 * @author Charles Hamilton
 */
public class RootNode extends Node {

    private int lastNodeID = -1;

    void setLastNodeID(int lastNodeID) {
        this.lastNodeID = lastNodeID;
    }

    int getLastNodeID() {
        return lastNodeID;
    }
}
