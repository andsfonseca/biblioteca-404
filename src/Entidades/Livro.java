package Entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Livro {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	
	@ManyToOne
	private Pessoa pessoa;
	
	public enum Disposicao{ DISPONIVEL, EMPRESTIMO; }
	
	@Enumerated(EnumType.STRING)
	private Disposicao disposicao;
	
	String imagem;
	String sinopse;
	String autor;
	
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getsinopse() {
		return sinopse;
	}
	public void setsinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getautor() {
		return autor;
	}
	public void setautor(String autor) {
		this.autor = autor;
	}
	
	
	public Disposicao getDisposicao() {
		return disposicao;
	}
	public void setDisposicao(Disposicao disposicao) {
		this.disposicao = disposicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public int getId() {
		return id;
	}
	
    

	

}
