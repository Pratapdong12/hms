package com.pratap.hms.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailUtil {

	//helps to send mail 
	public static void sendEmail(MailSender mailSender,String toEmail, String subject, String msg) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("rinzindong11@gmail.com");
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(msg);
		mailSender.send(mailMessage);
	}
}
