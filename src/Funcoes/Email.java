package Funcoes;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	SimpleEmail email = new SimpleEmail();

	public void SimpleMail(String nome, String mail, String msg, String title) throws EmailException {
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		email.addTo("andsf97@gmail.com", "Anderson Silva"); // destinatário
		email.setFrom(mail, nome); // remetente
		email.setSubject(title);// assunto do e-mail
		email.setMsg(msg); // conteudo do e-mail
		email.setAuthentication("andsf97@gmail.com", "");  
        email.setSmtpPort(465);  
        email.setSSL(true);  
        email.setTLS(true);  
		email.send();
	}

}
