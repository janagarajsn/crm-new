package com.wnwa.crm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String toEmail, String cc, String bcc,String subject, String body){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String[] emailAddresses = toEmail.split(",");

            helper.setTo(emailAddresses); // Set multiple email addresses separated by commas
            if (cc != null && !cc.isEmpty()) {
                helper.setCc(cc); // Set CC recipients
            }
            if (bcc != null && !bcc.isEmpty()) {
                helper.setBcc(bcc); // Set BCC recipients
            }
            helper.setSubject(subject);
            helper.setText(body);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "Mail Sent Successfully!!";
    }
}
