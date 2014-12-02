package Entidades;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Registro {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne
	private Livro livro;
	
	private Date date;
	private String notas;
	public enum Situacao{ ANDAMENTO, FINALIZADO; }
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	
	//Getters and Setters
	
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


}
