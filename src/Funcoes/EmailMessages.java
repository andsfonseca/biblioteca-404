package Funcoes;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import Entidades.Pessoa;

public class EmailMessages {

	public void esqueceSenha(Pessoa pessoa, String senha) {
		String title = "Biblioteca 404 - Esqueceu sua Senha";
		String msg = "Olá "
				+ pessoa.getNome()
				+ ".\n\nVerificamos que foi solicitado a troca de sua senha./nSua nova senha será redefinida para: "
				+ senha;
		String mail = pessoa.getEmail();
		String nome = pessoa.getNome();

		Email email = new Email();

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			
			email.SimpleMail(nome, mail, msg, title);
			context.addMessage(null, new FacesMessage("Atenção",
					"Um e-mail foi enviado para seu correio eletretrônico registrado. Verifique sua caixa de entrada ou sua caixa de spans"));
			System.out.println("Dados incorretos foram imformados");
		} catch (EmailException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro!",
					"INTERNAL_EMAIL_ERROR. "));
			e.printStackTrace();
		}

	}
}
