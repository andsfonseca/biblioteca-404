package Funcoes;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import DAO.DAO;
import Entidades.Pessoa;

public class PessoasLogin {

	public Pessoa efetuaLogin (Pessoa login) throws NoSuchAlgorithmException{
		FacesContext context = FacesContext.getCurrentInstance();
		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
		System.out.println("AQUI1");
		if(login.getUsername() == null && login.getSenha() == null){
			context.addMessage(null, new FacesMessage("Erro!" , "Dados incorretos") );
			System.out.println("Dados incorretos foram imformados");
		}
		else{
			String senha = new Criptografia().criptografia(login.getSenha());
			String cmd = "Select e from Pessoa e where e.username = '"+ login.getUsername() + "' and e.senha = '" + senha +"'" ;
			
			if(dao.validade(cmd)){
				List<Pessoa> listPessoa = dao.commandlineSingle(cmd);
				
				for(Pessoa pessoa: listPessoa){
					login = pessoa;
					
				}
			}
			
		}
		return login;
		
		
	}
	
}
