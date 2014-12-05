package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import DAO.DAO;
import Entidades.Livro;
import Entidades.Livro.Disposicao;
import Entidades.Pessoa;
import Funcoes.LivrosRegistros;

@SessionScoped
@ManagedBean(name = "bLivro")
public class LivrosBean {

	private LivrosRegistros LReg = new LivrosRegistros();
	private Livro livro = new Livro();
	private Pessoa pessoa = new Pessoa();
	private List<Livro> LivroList;
	private List<Pessoa> PessoaList;

	// Getters and Setters
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Livro> getLivroList() {

		// Checa se a lista já está completa
		if (LivroList == null) {
			LivroList = new DAO<Livro>(Livro.class).listar();
			System.out.println("Recuperando Lista de Livros no Banco de Dados");
		}
		return LivroList;
	}

	public void setLivroList(List<Livro> livroList) {
		LivroList = livroList;
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

	public void salvar() {
		LReg.salvar(livro, pessoa);
		atualizar();
		refreshPessoas();
	}

	public void atualizar() {
		this.livro = new Livro();
		this.LivroList = new DAO<Livro>(Livro.class).listar();
	}

	public void refreshPessoas() {
		this.pessoa = new Pessoa();
		this.PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
	}

	public void remover(Livro livro) {
		LReg.remover(livro);
		atualizar();
	}

	public void alterar(CellEditEvent event) {
		LReg.alterar(event, LivroList);
		atualizar();
	}

	
}
