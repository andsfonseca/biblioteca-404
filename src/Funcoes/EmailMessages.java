package Funcoes;

import Entidades.Pessoa;

public class EmailMessages {

	public void esqueceSenha(Pessoa pessoa){
		String title = "Biblioteca 404 - Esqueceu sua Senha";
		String msg ="Olá "+ pessoa.getNome() + ".\n\n";
		
	}
}
