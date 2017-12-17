/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habibsweb.commons.commonroutines.email;

import java.util.List;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Class for sending email. This glass uses the <code>javamail</code> class.
 * Found at http://www.oracle.com/technetwork/java/javamail/index-138643.html.
 *
 * @author Chrles Hamilton
 */
public class SendEmail {

    /**
     * Sends an email with files
     *
     * @param toAddress the address of the person to receive the email
     * @param subject the subject of the email
     * @param bodyMessage the message of the email
     * @param sender a class with the information to send the email
     * @param files list of files to be attached
     */
    public void sendEmail(String toAddress, String subject, String bodyMessage, SenderInfo sender, List<File> files) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", sender.getSMTPHostName());
        props.put("mail.smtp.port", String.valueOf(sender.getServerEmailPort()));

        // Get the Session object.
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getSenderAuthenticationID(), sender.getSenderAuthenticationPassword());
            }
        };
        Session session = Session.getInstance(props, authenticator);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getSenderAddress()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(bodyMessage);
            Multipart multi = new MimeMultipart();
            multi.addBodyPart(bodyPart);

            for (File file : files) {
                bodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                bodyPart.setDataHandler(new DataHandler(source));
                bodyPart.setFileName(file.getName());
                multi.addBodyPart(bodyPart);
            }

            message.setContent(multi);
            Transport.send(message);
        } catch (Exception e) {
        }
    }

    /**
     * Sends an email
     *
     * @param toAddress the address of the person to receive the email
     * @param subject the subject of the email
     * @param bodyMessage the message of the email
     * @param sender a class with the information to send the email
     */
    public void sendEmail(String toAddress, String subject, String bodyMessage, SenderInfo sender) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", sender.getSMTPHostName());
        props.put("mail.smtp.port", String.valueOf(sender.getServerEmailPort()));

        // Get the Session object.
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getSenderAuthenticationID(), sender.getSenderAuthenticationPassword());
            }
        };
        Session session = Session.getInstance(props, authenticator);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getSenderAddress()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(bodyMessage);

            Transport.send(message);
        } catch (Exception e) {
        }
    }
}
