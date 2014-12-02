package Funcoes;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import DAO.DAO;
import Entidades.Livro;
import Entidades.Livro.Disposicao;
import Entidades.Pessoa;

public class LivrosRegistros {

	public void salvar(Livro livro, Pessoa pessoa) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (livro.getNome() != "" || pessoa.getNome() != null) {
			DAO<Livro> dao = new DAO<Livro>(Livro.class);

			livro.setPessoa(pessoa);
			livro.setDisposicao(Disposicao.DISPONIVEL);
			dao.salva(livro);
			context.addMessage(null,
					new FacesMessage("Sucesso!", livro.getNome()
							+ " adicionado com sucesso!"));
			System.out.println("Livro Salvo com Sucesso no Banco de Dados");
		} else {
			context.addMessage(null, new FacesMessage("Erro!",
					"Complete os campos para cadastrar"));
		}

	}

	public void remover(Livro livro) {
		FacesContext context = FacesContext.getCurrentInstance();

		if (livro.getDisposicao() == Livro.Disposicao.DISPONIVEL) {
			DAO<Livro> dao = new DAO<Livro>(Livro.class);
			dao.remover(livro);
			context.addMessage(null,
					new FacesMessage("Sucesso!", livro.getNome()
							+ " removido com sucesso!"));
			System.out.println("Livro Removido com Sucesso no Banco de Dados");
		} else {
			context.addMessage(null, new FacesMessage("Erro!", livro.getNome()
					+ " não pode ser removido pois encontra-se emprestado"));
		}

	}

	public void alterar(CellEditEvent event, List<Livro> LivroList) {
		Object newValue = event.getNewValue();

		FacesContext context = FacesContext.getCurrentInstance();

		DAO<Livro> dao = new DAO<Livro>(Livro.class);

		for (Livro livro : LivroList) {
			if (livro.getNome() == (String) newValue) {
				livro.setNome((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
				context.addMessage(null,
						new FacesMessage("Sucesso!", livro.getNome()
								+ " alterado com sucesso!"));
			}
			if (livro.getautor() == (String) newValue) {
				livro.setautor((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
				context.addMessage(null,
						new FacesMessage("Sucesso!", livro.getNome()
								+ " alterado com sucesso!"));
			}
			if (livro.getsinopse() == (String) newValue) {
				livro.setsinopse((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
				context.addMessage(null,
						new FacesMessage("Sucesso!", livro.getNome()
								+ " alterado com sucesso!"));
			}
			if (livro.getImagem() == (String) newValue) {
				livro.setImagem((String) newValue);
				dao.atualizar(livro);
				System.out.println("Livro Salvo com Sucesso no Banco de Dados");
				context.addMessage(null,
						new FacesMessage("Sucesso!", livro.getNome()
								+ " alterado com sucesso!"));
			}

		}

	}

}
