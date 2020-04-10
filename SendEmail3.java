
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class SendEmail3 
{
	String user=null;
	String pass=null;
	String email=null;
	String add=null;

	final String username = "ekanboopathi@gmail.com";
	final String password = "Sembedu@29@16";
	public void setpass(String id,String pwd,String mail,String address)
	{
		user=id;
		pass=pwd;
		email=mail;
		add=address;
	}
public void email()
	{
		
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

	try {
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("ekanboopathi@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email));
		message.setSubject("Welcome Customer");
		MimeBodyPart messagepart1 = new MimeBodyPart();
		messagepart1.setText("Dear Customer, "+"\nYou have been Successfully Registered!!\n\nYour Login Credentials:");
		MimeBodyPart messagepart2 = new MimeBodyPart();
		messagepart2.setText("\n\nEmail:  "+email);
		MimeBodyPart messagepart3 = new MimeBodyPart();
		messagepart3.setText("\nPassword: "+pass);
		MimeBodyPart messagepart31 = new MimeBodyPart();
		
		messagepart31.setText("\n\n\n\n\n\n\n\n\n\n\nThanks & Regards\n\n"+add);
		 Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messagepart1);  
		    multipart.addBodyPart(messagepart2);
		    multipart.addBodyPart(messagepart3);
		    multipart.addBodyPart(messagepart31);
		    message.setContent(multipart );  
		Transport.send(message);

		System.out.println("Done");

	} catch (MessagingException e) {
		System.out.println(e);

		throw new RuntimeException(e);
	}

}

	}
