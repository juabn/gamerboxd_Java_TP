package servidor;



import java.util.Date;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;

public class GestionMail {
	
	
	
	
	

	public static void main(String[] args) {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");     
        props.put("mail.smtp.port", "587");                
        props.put("mail.smtp.auth", "true");              
        props.put("mail.smtp.starttls.enable", "true");
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom("gamerboxdoficial@gmail.com");
	        msg.setRecipients(Message.RecipientType.TO,
	                          "santiagomalet229@gmail.com");
	        msg.setSubject("Probando enviar mails");
	        msg.setSentDate(new Date());
	        msg.setText("Probando");
	        Transport.send(msg, "gamerboxdoficial@gmail.com", "qpbf siqy hrrg rkov");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }

	}
	
	
	public static void enviarmail(String destino,String asunto, String mensaje) {
		
		 Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.gmail.com");     
	        props.put("mail.smtp.port", "587");                
	        props.put("mail.smtp.auth", "true");              
	        props.put("mail.smtp.starttls.enable", "true");
		    Session session = Session.getInstance(props, null);

		    try {
		        MimeMessage msg = new MimeMessage(session);
		        msg.setFrom("gamerboxdoficial@gmail.com");
		        msg.setRecipients(Message.RecipientType.TO,
		                          destino);
		        msg.setSubject(asunto);
		        msg.setSentDate(new Date());
		        msg.setText(mensaje);
		        Transport.send(msg, "gamerboxdoficial@gmail.com", "qpbf siqy hrrg rkov");
		    } catch (MessagingException mex) {
		        System.out.println("send failed, exception: " + mex);
		    }
		
		
		
	} 

}
