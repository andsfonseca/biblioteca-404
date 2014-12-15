package Funcoes;

import Entidades.Pessoa;

public class EmailMessages {

	public void esqueceSenha(Pessoa pessoa, String senha, String link){
		String title = "Biblioteca 404 - Esqueceu sua Senha";
		String msg ="Olá "+ pessoa.getNome() + ".\n\nVerificamos que foi solicitado a troca de sua senha./nSua nova senha será redefinida para" + senha  + "/n/nOu você pode acessar o link diretamente por:\n" + link;
	}
}
