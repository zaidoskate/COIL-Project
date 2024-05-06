/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 *
 * @author zaido
 */
public class MailSender {
    private static final String USERNAME = "coilviczamatl@hotmail.com";
    private static final String PASSWORD = "zamatl123";
    private static final String HOST = "smtp-mail.outlook.com";
    private static final int PORT = 587;
    
    public static boolean sendEmail(String emailBody, String recipient) throws LogicException {
        boolean sent = false;
        try {
            Session session = createSession();
            Message message = createMessage(session, emailBody, recipient);
            send(message);
            sent = true;
        } catch(LogicException logicException) {
            throw logicException;
        }
        return sent;
    }
    
    private static Session createSession() {
        Properties emailProperties =  new Properties();
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        emailProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        emailProperties.put("mail.smtp.host", HOST);
        emailProperties.put("mail.smtp.port", PORT);
        
        Session session =  Session.getInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        return session;
    }
    
    private static Message createMessage(Session session, String emailBody, String recipient) throws LogicException {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Alerta del sistema COIL-VIC");
            message.setText(emailBody);
        } catch(MessagingException messagingException) {
            throw new LogicException("No se pudo notificar al usuario por correo", messagingException);
        }
        return message;
    }
    
    private static void send(Message message) throws LogicException {
        try {
            Transport.send(message);
        } catch(MessagingException messagingException) {
            throw new LogicException("Error al enviar el correo", messagingException);
        }
    }
}
