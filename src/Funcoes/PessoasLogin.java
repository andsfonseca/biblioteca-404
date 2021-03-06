package Funcoes;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import DAO.DAO;
import Entidades.Estado;
import Entidades.Pessoa;

public class PessoasLogin {

	public Pessoa efetuaLogin(Pessoa login) throws NoSuchAlgorithmException {
		FacesContext context = FacesContext.getCurrentInstance();

		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
		System.out.println("Verificando Login");
		if (login.getUsername() == null && login.getSenha() == null) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Dados incompletos"));
			System.out.println("Dados incorretos foram imformados");
		} else {
			String senha = new Criptografia().criptografia(login.getSenha());
			String cmd = "Select e from Pessoa e where e.username = '"
					+ login.getUsername() + "' and e.senha = '" + senha + "'";

			if (dao.validade(cmd)) {
				List<Pessoa> listPessoa = dao.commandlineSingle(cmd);

				for (Pessoa pessoa : listPessoa) {
					login = pessoa;
					context.addMessage(null,
							new FacesMessage("Login efetuado com sucesso",
									"Bem Vindo " + login.getNome()));

				}
			}else{
				context.addMessage(null, new FacesMessage("Erro!",
						"Usu�rio ou senha Incorretos"));
			}

		}
		return login;
	}

	public void esquecer(Pessoa login) {
		FacesContext context = FacesContext.getCurrentInstance();
		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);

		if (login.getUsername() == null && login.getEmail() == null) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Dados incorretos"));
			System.out.println("Dados incorretos foram imformados");
		} else {

			String cmd = "Select e from Pessoa e where e.username = '"
					+ login.getUsername() + "' and e.email = '"
					+ login.getEmail() + "'";
			System.out.println("Passou aqui1" + cmd);
			if (dao.validade(cmd)) {
				List<Pessoa> listPessoa = dao.commandlineSingle(cmd);
				System.out.println("Passou aqui12");
				for (Pessoa pessoa : listPessoa) {
					login = pessoa;
				}
				
				EmailMessages emsg = new EmailMessages();
				String senha = new GeraSenha().Gera();
				PessoasRegistros pl = new PessoasRegistros();
				Estado estado = new Estado();
				
				estado.setSenhaesquecida(true);
				login.setEstado(estado);
				pl.senhaAlterada(senha, login);
				emsg.esqueceSenha(login, senha);
				
			}

		}
	}

}
