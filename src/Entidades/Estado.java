package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue
	private int id;
	
	private boolean senhaesquecida;
	
	public boolean isSenhaesquecida() {
		return senhaesquecida;
	}

	public void setSenhaesquecida(boolean senhaesquecida) {
		this.senhaesquecida = senhaesquecida;
	}

	public int getId() {
		return id;
	}

	
}
