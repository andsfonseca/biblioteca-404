package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import Entidades.Registro;
import Util.JPAUtil;

public class DAO<T> {

	private final EntityManager em;
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.em = new JPAUtil().getEntityManager();
		this.classe = classe;
	}

	public void salva(T t) {
		this.em.getTransaction().begin();
		this.em.persist(t);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void remover(T t) {
		this.em.getTransaction().begin();
		this.em.remove(em.merge(t));
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void atualizar(T t) {
		this.em.getTransaction().begin();
		this.em.merge(t);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public T buscar(int id) {
		return em.getReference(classe, id);
	}

	//######LISTAGEM POR UM UNICO VALOR - Métodos Rápidos ####################################
																						//####
	public List<T> commandline(String comando) {										//####
		return em.createQuery(comando).getResultList();									//####
	}																					//####
																						//####
	public List<T> commandlineSingle(String comando) {									//####
		return em.createQuery(comando).getResultList();									//####
	}																					//####
																						//####
	public Boolean validade(String comando) {											//####
		return !em.createQuery(comando).getResultList().isEmpty();						//####
	}																					//####
																						//####
																						//####
	//########################################################################################
	public List<T> listar() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

}
