package Beans;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.DAO;
import Entidades.Livro;
import Entidades.Pessoa;
import Entidades.Registro;
import Entidades.Livro.Disposicao;
import Entidades.Registro.Situacao;

@SessionScoped
@ManagedBean(name = "bRegistro")
public class RegistroBean {

	private Registro registro = new Registro();
	private Livro livro = new Livro();
	private Pessoa pessoa = new Pessoa();
	private List<Registro> RegistroList;
	private ArrayList<Registro> DisponiveisList;
	private List<Livro> LivroList;
	private List<Pessoa> PessoaList;
	private String changeDis;
	
	//Getter and Setters
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
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
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<Registro> getRegistroList() {
		//Checa se a lista já está completa
		if(RegistroList == null){
			RegistroList = new DAO<Registro>(Registro.class).listar();
			System.out.println("Recuperando Lista de Livros no Banco de Dados");
			Collections.reverse(RegistroList);
		}
		
		
		return RegistroList;
	}
	public void setRegistroList(List<Registro> registroList) {
		RegistroList = registroList;
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
	public ArrayList<Registro> getDisponiveisList() {
		
		RegistroList = new DAO<Registro>(Registro.class).listar();
		LivroList = new DAO<Livro>(Livro.class).listar();
		
		ArrayList<Registro> aux = new ArrayList<Registro>();
		
		//int vetorId[] = new int[LivroList.size()];
		
		for (Livro livro: LivroList){
			if(livro.getDisposicao() == Disposicao.EMPRESTIMO){
				for(Registro registro: RegistroList){
					if(livro.getId() == registro.getLivro().getId() && registro.getSituacao() == Registro.Situacao.ANDAMENTO){
						aux.add(registro);
						
					}
				}
			}
		}
		DisponiveisList = aux;
			
		return DisponiveisList;
	}
	public void setDisponiveisList(ArrayList<Registro> disponiveisList) {
		DisponiveisList = disponiveisList;
	}
	public String getChangeDis() {
		return changeDis;
	}
	public void setChangeDis(String changeDis) {
		this.changeDis = changeDis;
	}
	
	//Métodos
	
	public void salvar (){
		FacesContext context = FacesContext.getCurrentInstance();
		if(livro.getNome() != "" || pessoa.getNome() != null){
			
			if(livro.getPessoa().getId() == pessoa.getId()){
				
				context.addMessage(null, new FacesMessage("Erro!" , "Não é possível emprestar o livro para o seu dono") );
				
			} else{
				
				if(livro.getDisposicao() == Livro.Disposicao.DISPONIVEL){
					DAO<Registro> dao = new DAO<Registro>(Registro.class);
					DAO<Livro> dao1 = new DAO<Livro>(Livro.class);
					Date date = new Date();
					
					livro.setDisposicao(Disposicao.EMPRESTIMO);
					registro.setSituacao(Situacao.ANDAMENTO);
					registro.setLivro(livro);
					
					registro.setPessoa(pessoa);
					registro.setDate(date);
					
					dao1.atualizar(livro);
					dao.salva(registro);
					
					context.addMessage(null, new FacesMessage("Sucesso!" , livro.getNome() + " emprestado para " + pessoa.getNome()) );
					System.out.println("Livro Salvo com Sucesso no Banco de Dados");
				} else{
					context.addMessage(null, new FacesMessage("Erro!" , "Livro já foi emprestado") );
				}
			}
		}
		else{
			context.addMessage(null, new FacesMessage("Erro!" , "Complete os campos para cadastrar") );
		}
		
		this.livro = new Livro();
		this.LivroList = new DAO<Livro>(Livro.class).listar();
		this.pessoa = new Pessoa();
		this.PessoaList = new DAO<Pessoa>(Pessoa.class).listar();
		atualizar();
	
	}
	
	public void atualizar() {
		this.registro = new Registro();
		this.RegistroList = new DAO<Registro>(Registro.class).listar();
		Collections.reverse(RegistroList);
	}
	
	public void alterar (Registro reg){
		FacesContext context = FacesContext.getCurrentInstance();
		DAO<Registro> dao = new DAO<Registro>(Registro.class);
		DAO<Livro> dao1 = new DAO<Livro>(Livro.class);
		LivroList = new DAO<Livro>(Livro.class).listar();
		for(Livro livro: LivroList){
			if(reg.getLivro().getId() == livro.getId()){
				reg.setSituacao(Situacao.FINALIZADO);
				livro.setDisposicao(Livro.Disposicao.DISPONIVEL);
				dao1.atualizar(livro);
				dao.atualizar(reg);
				
				context.addMessage(null, new FacesMessage("Sucesso!" , livro.getNome() + " foi devolvido para a Biblioteca por " + reg.getPessoa().getNome()) );
			}
		}
		
		this.registro = new Registro();
		this.RegistroList = new DAO<Registro>(Registro.class).listar();
		Collections.reverse(RegistroList);
		this.livro = new Livro();
		this.LivroList = new DAO<Livro>(Livro.class).listar();
		
       
	}

}

	