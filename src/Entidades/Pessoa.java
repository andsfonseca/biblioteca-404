package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.ejb.criteria.expression.function.LowerFunction;

import Funcoes.Criptografia;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue
	private int id;

	private String nome;
	private String username;
	private String email;
	private String emailhash;
	private String senha;
	private Boolean adm;
	
	@OneToOne
	private Estado estado;

	
	public String getEmailhash() {
		return emailhash;
	}

	public String getNome() {
		return nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		emailhash = new Criptografia().md5Hex(email.trim().toLowerCase());
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
