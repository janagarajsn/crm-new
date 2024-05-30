package com.wnwa.crm.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    private MimeMessage mimeMessage;

    @BeforeEach
    public void setUp() {
        mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    public void testSendEmail() throws MessagingException {
        // Given
        String toEmail = "test@example.com";
        String cc = "cc@example.com";
        String bcc = "bcc@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Mock the MimeMessageHelper
        doNothing().when(mailSender).send(any(MimeMessage.class));
        
        // When
        String result = emailService.sendEmail(toEmail, cc, bcc, subject, body);

        // Then
        assertEquals("Mail Sent Successfully!!", result);

        // Verify the interactions
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(mimeMessage);

        // Capture the MimeMessageHelper settings
        helper.setTo(toEmail);
        helper.setCc(cc);
        helper.setBcc(bcc);
        helper.setSubject(subject);
        helper.setText(body);

        // Assertions for the email settings
        verify(mimeMessage, times(1)).setRecipients(MimeMessage.RecipientType.TO, toEmail);
        verify(mimeMessage, times(1)).setRecipients(MimeMessage.RecipientType.CC, cc);
        verify(mimeMessage, times(1)).setRecipients(MimeMessage.RecipientType.BCC, bcc);
        verify(mimeMessage, times(1)).setSubject(subject);
        verify(mimeMessage, times(1)).setText(body);
    }
}
