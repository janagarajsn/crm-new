package com.wnwa.crm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String toEmail,String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rgita@velansolutions.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

       mailSender.send(message);
        return "Mail sent successfully...";
    }

}
