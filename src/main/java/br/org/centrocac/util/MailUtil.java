/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author toshiaki
 */
public class MailUtil {

    // GMAIL TOSHIAKI TEST

    private final String EMAIL_REMETENTE_PASSWORD = "toshiaki123";
    private final String EMAIL_FROM = "lucastsutsumihobby@gmail.com";

    private Properties getProperties() {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

    private Session getSession() {
        Properties props = getProperties();

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_FROM, EMAIL_REMETENTE_PASSWORD);
                    }
                });
        return session;
    }

    public boolean sendEmailToWithoutAttachment(String to) {

        Session session = getSession();

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM)); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(to);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com JavaMail");//Assunto
            message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Feito!!!");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
