package Beans;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.primefaces.event.CellEditEvent;

import DAO.DAO;
import Entidades.Livro;
import Entidades.Pessoa;
import Entidades.Registro;
import Funcoes.Email;
import Funcoes.LivrosRegistros;
import Funcoes.PessoasLogin;
import Funcoes.PessoasRegistros;

@SessionScoped
@ManagedBean(name = "bPessoa")
public class PessoasBean {

	private PessoasRegistros PReg = new PessoasRegistros();
	private PessoasLogin PLog = new PessoasLogin();
	private Pessoa pessoa = new Pessoa();
	private Pessoa login = new Pessoa();
	private List<Pessoa> PessoaList;
	private Livro livro = new Livro();
	private String check;
	private String check2;

	// Getters and Setters

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public Pessoa getLogin() {
		
		return login;
	}

	public void setLogin(Pessoa login) {
		this.login = login;
		if(!login.getEstado().isSenhaesquecida())
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "É necessário que mude sua senha!"));
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoaList() {

		// Checa se a lista já está completa
		if (PessoaList == null) {
			PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
			System.out
					.println("Recuperando Lista de Usuário no Banco de Dados");
		}

		return PessoaList;
	}

	public void setPessoaList(List<Pessoa> pessoaList) {
		PessoaList = pessoaList;
	}

	// Métodos
	public void atualizar() {
		this.pessoa = new Pessoa();
		this.PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
	}

	public void salvarADM() throws NoSuchAlgorithmException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (check.equals(pessoa.getSenha())) {

			PReg.salvar(pessoa);
		} else {
			context.addMessage(null, new FacesMessage("Erro!",
					"Senhas não conferem"));
		}
		atualizar();
		check = new String();

	}

	public void remover(Pessoa pessoa) {
		PReg.remover(pessoa);
		atualizar();
	}

	public void alterar(CellEditEvent event) {
		PReg.alterar(event, PessoaList);

		atualizar();

	}

	public void elogin() throws NoSuchAlgorithmException {
		this.login = PLog.efetuaLogin(login);
	}

	public void logout() {
		this.login = new Pessoa();

	}

	public void salvarLivro() {
		LivrosRegistros LReg = new LivrosRegistros();
		LReg.salvar(livro, login);
	}

	public boolean logado() {
		return login.getNome() != null;
	}
	
	public void esqueceSenha(){
		
		PLog.esquecer(login);
	}

	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}
	
	public void mudaSenha() throws NoSuchAlgorithmException{
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (check.equals(pessoa.getSenha())) {

			PReg.salvar(pessoa);
		} else {
			context.addMessage(null, new FacesMessage("Erro!",
					"Senhas não conferem"));
		}
	}

}
