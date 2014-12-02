package Funcoes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import DAO.DAO;
import Entidades.Livro;
import Entidades.Pessoa;
import Entidades.Registro;

public class PessoasRegistros {

	public void salvar (Pessoa pessoa){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);

		dao.salva(pessoa);
		context.addMessage(null, new FacesMessage("Sucesso!" , pessoa.getNome() + " adicionado com sucesso!") );
		System.out.println("Usuário Salvo com Sucesso no Banco de Dados");
        
	}
	
	public void remover(Pessoa pessoa){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Livro livro = new Livro();
		
		List<Livro> LivroList = new DAO<Livro>(Livro.class).listar();;
		
		List<Registro> RegistroList = new DAO<Registro>(Registro.class).listar();;
		ArrayList<Registro> RegistroList2 = new ArrayList<Registro> ();
		
		boolean check = false;
		boolean check2 = false;
		
		
		for(Registro registro: RegistroList){
			if(registro.getPessoa().getId() == pessoa.getId()){
				RegistroList2.add(registro);
			}
		}
		
		for(Registro registro : RegistroList2){
			if(registro.getLivro().getDisposicao() == Livro.Disposicao.EMPRESTIMO){
				check = true;
			}
		}
		
		for(Livro livr: LivroList){
			if (livr.getPessoa().getId() == pessoa.getId()){
				check2 = true;
			}
		}
		
		if(check == false && check2 == false){
			DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
			dao.remover(pessoa);
			context.addMessage(null, new FacesMessage("Sucesso!" , pessoa.getNome() + " removido com sucesso!") );
			System.out.println("Usuário Removido com Sucesso no Banco de Dados");
		}
		
		else if(check2 == true){
			context.addMessage(null, new FacesMessage("Erro!" , pessoa.getNome() + " não pode ser removido pois possui livros cadastrados") );
		}
		else{
			context.addMessage(null, new FacesMessage("Erro!" , pessoa.getNome() + " não pode ser removido pois precisa devolver seu emprestimo!") );
		}
		
	}
	
	public void alterar (CellEditEvent event, List<Pessoa> PessoaList){
        Object newValue = event.getNewValue();
         
		FacesContext context = FacesContext.getCurrentInstance();
		
		DAO<Pessoa> dao = new DAO<Pessoa>(Pessoa.class);
		
		for(Pessoa pessoa: PessoaList){
			if(pessoa.getNome() == (String)newValue){
				pessoa.setNome((String) newValue);
				dao.atualizar(pessoa);
				System.out.println("Usuário Salvo com Sucesso no Banco de Dados");
				context.addMessage(null, new FacesMessage("Sucesso!" , pessoa.getNome() + " alterado com sucesso!") );
			}

		}
			
	}
}
