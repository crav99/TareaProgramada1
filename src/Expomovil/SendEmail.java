/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expomovil;

import java.awt.HeadlessException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Allan, Marco, Maria José
 */

public class SendEmail {
    String host;
    String user;
    String password;
    String to;
    String from;
    String message;
    String subject;
    
    /**
     *
     * @param to
     * @param message
     * @param subject
     */
    public SendEmail(String to, String message, String subject){
        this.host = "smtp.gmail.com";
        this.user = "avrg99@gmail.com";
        this.password = "CRav99..";
        this.message = message;
        this.to = to;
        this.from = "ExpoMovil";
        this.subject = subject;
    }
    
    /**
     *
     */
    public void send(){
        try{
            String messageText = this.message;
            boolean sessionDebug = false;
            
            Properties props = System.getProperties();
            
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(sessionDebug);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = new InternetAddress[1];
            address[0] =  (new InternetAddress(to));
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);
            
            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(host, user, password);
                transport.sendMessage(msg, msg.getAllRecipients());
            }
            JOptionPane.showMessageDialog(null, "Enviado");
        }catch (HeadlessException | MessagingException e){
            System.out.println(e);
        }
        
    }
    
}
