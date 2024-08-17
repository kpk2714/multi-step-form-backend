package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailsender;
	
	
	public void sendEmail(String toEmail , String subject , String body) throws MessagingException {
			
			MimeMessage mimeMessage =  mailsender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage , true);
			message.setFrom("onlineexamportalboot@gmail.com");
			message.setTo(toEmail);
			message.setSubject(subject);
			message.setText(body);

			mailsender.send(mimeMessage);
		}
}
