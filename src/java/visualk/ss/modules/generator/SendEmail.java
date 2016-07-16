package visualk.ss.modules.generator;

//apt-get install sendmail

import java.io.IOException;
import java.io.PrintStream;

import sun.net.smtp.SmtpClient;

public class SendEmail {

	public SendEmail(){
		
	}
	public void sendInvitation(String to,String code){
	String from="camps.alex@gmail.com";
		try{
			SmtpClient client = new SmtpClient("localhost");
			client.from(from);
			client.to(to);
			PrintStream message = client.startMessage();
			message.println("To: " + to);
			message.println("Subject: SurveyServer");
			message.println("Has estat escollit per a realitzar una enquesta.");
			message.println();
			message.println("Ves a http://www.surveyserver.com i entra el codi : "+code);
			message.println();
			message.println();
			client.closeServer();
		}
		catch (IOException e){
			System.out.println("ERROR SENDING EMAIL:"+e);
		}
	}
}

