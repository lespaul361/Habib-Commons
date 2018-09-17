/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.email;

/**
 *
 * @author David
 */
public class SenderInfo {

    private String senderAddress;
    private String senderAuthenticationID;
    private String senderAuthenticationPassword;

    private int serverEmailPort;
    private String SMTPHostName;
    private String hostName;

    /**
     * Constructs an empty <code>SenderInfo</code> class
     */
    public SenderInfo() {
    }

    /**
     * Constructs an new <code>SenderInfo</code> class
     *
     * @param senderAddress the email address of the sender
     * @param senderAuthenticationID the login id of the sender
     * @param senderAuthenticationPassword the login password of the sender
     * @param SMTPHostName the server address
     * @param port the port used to send email
     */
    public SenderInfo(String senderAddress, String senderAuthenticationID, String senderAuthenticationPassword,
            String SMTPHostName, int port) {
        this.senderAddress = senderAddress;
        this.senderAuthenticationID = senderAuthenticationID;
        this.senderAuthenticationPassword = senderAuthenticationPassword;
        this.SMTPHostName = SMTPHostName;
        this.serverEmailPort = port;
    }

    /**
     * @return the senderAddress
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * @param senderAddress the senderAddress to set
     */
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    /**
     * @return the senderAuthenticationID
     */
    public String getSenderAuthenticationID() {
        return senderAuthenticationID;
    }

    /**
     * @param senderAuthenticationID the senderAuthenticationID to set
     */
    public void setSenderAuthenticationID(String senderAuthenticationID) {
        this.senderAuthenticationID = senderAuthenticationID;
    }

    /**
     * @return the senderAuthenticationPassword
     */
    public String getSenderAuthenticationPassword() {
        return senderAuthenticationPassword;
    }

    /**
     * @param senderAuthenticationPassword the senderAuthenticationPassword to
     * set
     */
    public void setSenderAuthenticationPassword(String senderAuthenticationPassword) {
        this.senderAuthenticationPassword = senderAuthenticationPassword;
    }

    /**
     * @return the serverEmailPort
     */
    public int getServerEmailPort() {
        return serverEmailPort;
    }

    /**
     * @param serverEmailPort the serverEmailPort to set
     */
    public void setServerEmailPort(int serverEmailPort) {
        this.serverEmailPort = serverEmailPort;
    }

    /**
     * @return the SMTPHostName
     */
    public String getSMTPHostName() {
        return SMTPHostName;
    }

    /**
     * @param SMTPHostName the SMTPHostName to set
     */
    public void setSMTPHostName(String SMTPHostName) {
        this.SMTPHostName = SMTPHostName;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
