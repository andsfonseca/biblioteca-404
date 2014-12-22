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
		System.out.println("Procurando no Banco de Dados algum usúario administrador...");
		String cmd = "Select e from Pessoa e where e.adm = true";

		if (dao.validade(cmd)) {
			System.out.println("Existe no Banco um usuário administrador, requisição negada");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Erro!",
					"Já existe um usuario como administrador, contate-o! Foi enviado uma requisicao para o nosso sistema, caso problema nao seja resolvida entre em contato conosco"));
		}
		else{
			Pessoa pessoa = new Pessoa();
			System.out.println("Usuário administrador não foi encontrado, o sistema irá criar um usuário administrador...");
			pessoa.setAdm(true);
			pessoa.setEmail("adm@adm.com");
			pessoa.setNome("Administrador");
			pessoa.setSenha("adm");
			pessoa.setUsername("adm");
			System.out.println("Aplicando permissões de Administrador ao Usuário");
			PessoasRegistros pr = new PessoasRegistros();
			pr.salvar(pessoa);
			
			
		}
	}
}
