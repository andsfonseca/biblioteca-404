package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.DAO;
import Entidades.Pessoa;

@SessionScoped
@ManagedBean(name = "bLogin")
public class LoginBean {
	
	private Pessoa pessoa = new Pessoa();
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void efetuaLogin(){
		FacesContext context = FacesContext.getCurrentInstance();
		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
		System.out.println("AQUI1");
		if(pessoa.getUsername() == null && pessoa.getSenha() == null){
			context.addMessage(null, new FacesMessage("Erro!" , "Dados incorretos") );
			System.out.println("Aqui 2" + pessoa.getUsername() + pessoa.getSenha());
		}
		else{
			String cmd = "Select e from Pessoa e where e.username = '"+ pessoa.getUsername() + "' and e.senha = '" + pessoa.getSenha() +"'" ;
			
			if(dao.validade(cmd)){
				List<Pessoa> listPessoa = dao.commandlineSingle(cmd);
				
				for(Pessoa pessoa: listPessoa){
					this.pessoa = pessoa;
					
				}
			}
			
		}
		
		
	}

	public void logout(){
		this.pessoa = new Pessoa();
		
	}

	
}
