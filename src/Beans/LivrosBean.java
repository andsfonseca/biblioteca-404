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

@SessionScoped
@ManagedBean(name = "bLivro")
public class LivrosBean {
	
	private Livro livro = new Livro();
	private Pessoa pessoa = new Pessoa();
	private List<Livro> LivroList;
	private List<Pessoa> PessoaList;

	//Getters and Setters
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
		
		//Checa se a lista já está completa
		if(LivroList == null){
			LivroList = new DAO<Livro>(Livro.class).listar();
			System.out.println("Recuperando Lista de Livros no Banco de Dados");
		}
		return LivroList;
	}
	public void setLivroList(List<Livro> livroList) {
		LivroList = livroList;
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
	
	public void salvar (){
		FacesContext context = FacesContext.getCurrentInstance();
		if(livro.getNome() != "" || pessoa.getNome() != null){
			DAO<Livro> dao = new DAO<Livro>(Livro.class);
			
			livro.setPessoa(pessoa);
			livro.setDisposicao(Disposicao.DISPONIVEL);
			dao.salva(livro);
			context.addMessage(null, new FacesMessage("Sucesso!" , livro.getNome() + " adicionado com sucesso!") );
			System.out.println("Livro Salvo com Sucesso no Banco de Dados");
		}
		else{
			context.addMessage(null, new FacesMessage("Erro!" , "Complete os campos para cadastrar") );
		}
		
		atualizar();
		refreshPessoas();
	
	}
	
	public void atualizar(){
		this.livro = new Livro();
		this.LivroList = new DAO<Livro>(Livro.class).listar();
		
		
	}
	
	public void refreshPessoas(){
		this.pessoa = new Pessoa();
		this.PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
		
	}
	
	public void remover(Livro livro){
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(livro.getDisposicao() == Livro.Disposicao.DISPONIVEL){
			DAO<Livro> dao = new DAO<Livro>(Livro.class);
			dao.remover(livro);
			context.addMessage(null, new FacesMessage("Sucesso!" , livro.getNome() + " removido com sucesso!") );
			System.out.println("Livro Removido com Sucesso no Banco de Dados");
			
		}
		
		else{
			context.addMessage(null, new FacesMessage("Erro!" , livro.getNome() + " não pode ser removido pois encontra-se emprestado") );
		}
		
		atualizar();
	}
	
	public void alterar (CellEditEvent event){
        Object newValue = event.getNewValue();
         
		FacesContext context = FacesContext.getCurrentInstance();
		
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		
		for(Livro livro: LivroList){
			if(livro.getNome() == (String)newValue){
				livro.setNome((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
			}
			if(livro.getautor() == (String)newValue){
				livro.setautor((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
			}
			if(livro.getsinopse() == (String)newValue){
				livro.setsinopse((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
			}
			if(livro.getImagem() == (String)newValue){
				livro.setImagem((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
			}

		}
		
		context.addMessage(null, new FacesMessage("Sucesso!" , livro.getNome() + " alterado com sucesso!") );
		
		atualizar();
		
	}
	
	}
