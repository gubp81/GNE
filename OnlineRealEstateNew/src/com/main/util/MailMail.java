package com.main.util;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
 
public class MailMail
{
	private MailSender mailSender;
 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		try {
			SimpleMailMessage message = new SimpleMailMessage();
 
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(msg);
			mailSender.send(message);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}