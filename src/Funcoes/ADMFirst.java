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
		System.out.println("Procurando no Banco de Dados algum us�ario administrador...");
		String cmd = "Select e from Pessoa e where e.adm = true";

		if (dao.validade(cmd)) {
			System.out.println("Existe no Banco um usu�rio administrador, requisi��o negada");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Erro!",
					"J� existe um usuario como administrador, contate-o! Foi enviado uma requisicao para o nosso sistema, caso problema nao seja resolvida entre em contato conosco"));
		}
		else{
			Pessoa pessoa = new Pessoa();
			System.out.println("Usu�rio administrador n�o foi encontrado, o sistema ir� criar um usu�rio administrador...");
			pessoa.setAdm(true);
			pessoa.setEmail("adm@adm.com");
			pessoa.setNome("Administrador");
			pessoa.setSenha("adm");
			pessoa.setUsername("adm");
			System.out.println("Aplicando permiss�es de Administrador ao Usu�rio");
			PessoasRegistros pr = new PessoasRegistros();
			pr.salvar(pessoa);
			
			
		}
	}
}
