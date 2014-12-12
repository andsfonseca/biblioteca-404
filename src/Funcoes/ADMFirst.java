package Funcoes;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import DAO.DAO;
import Entidades.Pessoa;

public class ADMFirst {

	public void admFirst() throws NoSuchAlgorithmException {
		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
		FacesContext context = FacesContext.getCurrentInstance();
		String cmd = "Select e from Pessoa e where e.adm = true";

		if (dao.validade(cmd)) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Ja Existe um usuario como administrador, contate-o! Foi enviado uma requisicao para o nosso sistema, caso problema nao seja resolvida entre em contato conosco"));
		}
		else{
			Pessoa pessoa = new Pessoa();
			
			pessoa.setAdm(true);
			pessoa.setEmail("adm@adm.com");
			pessoa.setNome("Administrador");
			pessoa.setSenha("adm");
			pessoa.setUsername("adm");
			
			PessoasRegistros pr = new PessoasRegistros();
			pr.salvar(pessoa);
			
		}
	}
}
