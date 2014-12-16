package Funcoes;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import Entidades.Pessoa;

public class EmailMessages {

	public void esqueceSenha(Pessoa pessoa, String senha, String link) {
		String title = "Biblioteca 404 - Esqueceu sua Senha";
		String msg = "Olá "
				+ pessoa.getNome()
				+ ".\n\nVerificamos que foi solicitado a troca de sua senha./nSua nova senha será redefinida para"
				+ senha + "/n/nOu vocé pode acessar o link diretamente por:\n"
				+ link;
		String mail = pessoa.getEmail();
		String nome = pessoa.getNome();

		Email email = new Email();

		try {
			email.SimpleMail(nome, mail, msg, title);
		} catch (EmailException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro!",
					"INTERNAL_EMAIL_ERROR. "));
			e.printStackTrace();
		}

	}
}
