package Funcoes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import DAO.DAO;
import Entidades.Estado;
import Entidades.Livro;
import Entidades.Pessoa;
import Entidades.Registro;

public class PessoasRegistros {
	DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);

	public void salvar(Pessoa pessoa) throws NoSuchAlgorithmException {
		
		FacesContext context = FacesContext.getCurrentInstance();

		String cmd = "Select e from Pessoa e where e.username = '"
				+ pessoa.getUsername() + "'";
		if (dao.validade(cmd)) {
			context.addMessage(null, new FacesMessage("Erro!",
					"Este nome de usuário já existe"));
		}

		else {
			Criptografia c = new Criptografia();
			Estado estado = new Estado();
			estado.setSenhaesquecida(false);
			pessoa.setSenha(c.criptografia(pessoa.getSenha()));
			pessoa.setEstado(estado);

			dao.salva(pessoa);
			context.addMessage(null,
					new FacesMessage("Sucesso!", pessoa.getNome()
							+ " adicionado com sucesso!"));
			System.out.println("Usuário Salvo com Sucesso no Banco de Dados");
		}
	}

	public void remover(Pessoa pessoa) {

		FacesContext context = FacesContext.getCurrentInstance();

		Livro livro = new Livro();

		List<Livro> LivroList = new DAO<Livro>(Livro.class).listar();
		;

		List<Registro> RegistroList = new DAO<Registro>(Registro.class)
				.listar();
		;
		ArrayList<Registro> RegistroList2 = new ArrayList<Registro>();

		boolean check = false;
		boolean check2 = false;

		for (Registro registro : RegistroList) {
			if (registro.getPessoa().getId() == pessoa.getId()) {
				RegistroList2.add(registro);
			}
		}

		for (Registro registro : RegistroList2) {
			if (registro.getLivro().getDisposicao() == Livro.Disposicao.EMPRESTIMO) {
				check = true;
			}
		}

		for (Livro livr : LivroList) {
			if (livr.getPessoa().getId() == pessoa.getId()) {
				check2 = true;
			}
		}

		if (check == false && check2 == false) {
			DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
			dao.remover(pessoa);
			context.addMessage(null,
					new FacesMessage("Sucesso!", pessoa.getNome()
							+ " removido com sucesso!"));
			System.out
					.println("Usuário Removido com Sucesso no Banco de Dados");
		}

		else if (check2 == true) {
			context.addMessage(null, new FacesMessage("Erro!", pessoa.getNome()
					+ " não pode ser removido pois possui livros cadastrados"));
		} else {
			context.addMessage(
					null,
					new FacesMessage(
							"Erro!",
							pessoa.getNome()
									+ " não pode ser removido pois precisa devolver seu emprestimo!"));
		}

	}

	public void alterar(CellEditEvent event, List<Pessoa> PessoaList) {
		Object newValue = event.getNewValue();

		FacesContext context = FacesContext.getCurrentInstance();

		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);

		for (Pessoa pessoa : PessoaList) {
			if (pessoa.getNome() == (String) newValue) {
				pessoa.setNome((String) newValue);
				dao.atualizar(pessoa);
				System.out
						.println("Usuário Salvo com Sucesso no Banco de Dados");
				context.addMessage(null,
						new FacesMessage("Sucesso!", pessoa.getNome()
								+ " alterado com sucesso!"));
			}

		}

	}

	public Pessoa peopleRequest(Pessoa pessoa) {

		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
		String cmd = "Select e from Pessoa e where e.username = '"
				+ pessoa.getUsername() + "'";

		if (dao.validade(cmd)) {
			List<Pessoa> listPessoa = dao.commandlineSingle(cmd);

			for (Pessoa p : listPessoa) {
				pessoa = p;

			}
		}

		return pessoa;
	}

	public void senhaAlterada(String senha, Pessoa login){
		
		try {
			login.setSenha(new Criptografia().criptografia(senha));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		dao.atualizar(login);
		
	}

public void mudaSenha(Pessoa pessoa, Pessoa login, String senhaAtual) throws NoSuchAlgorithmException{
	FacesContext context = FacesContext.getCurrentInstance();
	if(login.getSenha().equals(new Criptografia().criptografia(senhaAtual))){
		login.setSenha(new Criptografia().criptografia(pessoa.getSenha()));
		dao.atualizar(login);
		context.addMessage(null,
				new FacesMessage("Sucesso!",  "A sua senha foi alterada com sucesso!"));
	}else{
		context.addMessage(null,
				new FacesMessage("Erro", "Senha Incorreta"));
	}
}

}

