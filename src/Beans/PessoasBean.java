package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import DAO.DAO;
import Entidades.Livro;
import Entidades.Pessoa;
import Entidades.Registro;
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
	private LivrosBean lb;
	
	
	// Getters and Setters 
	public Pessoa getPessoa() {
		return pessoa;
	}
	public Pessoa getLogin() {
		return login;
	}
	public void setLogin(Pessoa login) {
		this.login = login;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<Pessoa> getPessoaList() {
		
		//Checa se a lista já está completa
		if(PessoaList == null){
			PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
			System.out.println("Recuperando Lista de Usuário no Banco de Dados");
		}
		
		return PessoaList;
	}
	public void setPessoaList(List<Pessoa> pessoaList) {
		PessoaList = pessoaList;
	}
	
	
	//Métodos
	public void atualizar() {
		this.pessoa = new Pessoa();
		this.PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
	}
	public void salvarADM (){
		PReg.salvar(pessoa);
		atualizar();
	}
	public void remover(Pessoa pessoa){
		PReg.remover(pessoa);
		atualizar();
	}
	public void alterar (CellEditEvent event){
        PReg.alterar(event, PessoaList);
		
		lb.atualizar();
		atualizar();
		
	}
	
	public void elogin(){
		this.login = PLog.efetuaLogin(login);
	}
	
	public void logout(){
		this.login = new Pessoa();
		
	}
	
	
}
