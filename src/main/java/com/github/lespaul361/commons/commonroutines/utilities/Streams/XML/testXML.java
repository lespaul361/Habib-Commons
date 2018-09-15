package com.github.lespaul361.commons.commonroutines.utilities.Streams.XML;

import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author David Hamilton
 */
public class testXML {

    public static void main(String[] args) {
	try {
	    File fle = new File("D:\\EbayUploads\\1524511960094.xml");
	    com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.XMLStream xmls = new com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.XMLStream(
		    fle);
	} catch (Exception e) {
	}

    }
}
