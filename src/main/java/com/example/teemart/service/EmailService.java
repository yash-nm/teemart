package com.example.teemart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	// Does not rely/depend on request ; no matter how many requests have sent (user) ; it doesn't affect this function
	@Async
	public void sendEmail(String toEmail,String subject,String message)
	{
	SimpleMailMessage mailMessage=new SimpleMailMessage();
	///mailMessage.setTo("yashmirashi59@gmail.com");
	mailMessage.setTo(toEmail);
	mailMessage.setSubject(subject);
	mailMessage.setText(message);
	mailMessage.setFrom("yashmirashi59@gmail.com");
	javaMailSender.send(mailMessage);

	}
	
	@Async
	public void sendHtmlEmail(String to, String subject, String htmlContent) {
	    MimeMessage message = javaMailSender.createMimeMessage();

	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			/* helper.setTo("yashmirashi59@gmail.com"); */
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(htmlContent, true); // second param = true to enable HTML
	        javaMailSender.send(message); 
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}

}
