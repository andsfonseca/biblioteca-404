package Funcoes;

import java.util.UUID;

public class GeraSenha {

	public String Gera(){
		UUID uuid = UUID.randomUUID();    
		String myRandom = uuid.toString();    
		return (myRandom.substring(0,6));
	}
}
