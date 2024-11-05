package com.mygym.security.services.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    public JavaMailSender emailSender;


    public static final String MAILADMIN = "qpuiggali@fisersa.cat";


//    @Autowired
//    public NotificationService(JavaMailSender javaMailSender){
//        this.javaMailSender = javaMailSender;
//    }


    @Async
    public void sendNotificaitoinToEmail(String email, String missatge, String subject) throws MailException, InterruptedException {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mail = new MimeMessageHelper(message, true);
            mail.setTo(email);
            mail.setFrom("no-reply@fisersa.cat");
            mail.setSubject(subject);
            mail.setText(missatge, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificaitoinToEmailSyncronous(String email, String missatge, String subject) throws MailException, MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message, true);
        mail.setTo(email);
        mail.setFrom("no-reply@fisersa.cat");
        mail.setSubject(subject);
        mail.setText(missatge, true);
        emailSender.send(message);
    }
    public void sendNotificaitoinToEmailSyncronous(String[] email, String missatge, String subject) throws MailException, MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message, true);
        mail.setTo(email);
        mail.setFrom("no-reply@fisersa.cat");
        mail.setSubject(subject);
        mail.setText(missatge, true);
        emailSender.send(message);
    }

    @Async
    public void sendNotificaitoinToEmail(String email, String missatge, String subject, byte[] content) throws MailException, InterruptedException {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mail = new MimeMessageHelper(message, true);
            mail.setTo(email);
            mail.setFrom("no-reply@fisersa.cat");
            mail.setSubject(subject);
            mail.addAttachment("Resposta.pdf", new ByteArrayResource(content));
            mail.setText(missatge, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendNotificaitoinToEmail(String email, String missatge, String subject, String cc) throws MailException, InterruptedException {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mail = new MimeMessageHelper(message, true);
            mail.setTo(email);
            mail.setFrom("no-reply@fisersa.cat");
            mail.setCc(cc);
            mail.setSubject(subject);
            mail.setText(missatge, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Async
    public void sendErrorMail(String subject, String text) {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        System.out.println("Sending email...");
//        System.err.println(text);
//        mail.setTo(MAILADMIN);
//        mail.setFrom("no-reply@fisersa.cat");
//        mail.setSubject(subject);
//        mail.setText(text);
//        emailSender.send(mail);
    }

}
