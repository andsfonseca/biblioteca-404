package Funcoes;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	SimpleEmail email = new SimpleEmail();

	public void SimpleMail(String Nome, String mail, String msg, String title) throws EmailException {
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		email.addTo("andsf97@gmail.com", "Tu"); // destinatário
		email.setFrom("andsf@live.com", "Eu"); // remetente
		email.setSubject("Mensagem de Teste");// assunto do e-mail
		email.setMsg("Teste de Email utilizando commons-email"); // conteudo do e-mail
		email.setAuthentication("andsf97@gmail.com", "");  
        email.setSmtpPort(465);  
        email.setSSL(true);  
        email.setTLS(true);  
		email.send();
	}

}
