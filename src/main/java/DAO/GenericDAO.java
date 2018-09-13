package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Abstract class of Access to Database, where are defined generic methods for
 * manipulation of objects.
 *
 * @author bruno
 */
public abstract class GenericDAO<T> {

	private Class<T> entityClass;

	protected abstract EntityManager getEntityManager();

	/**
	 * Builder with entityClass
	 * 
	 * @param entityClass
	 */
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Method to persist a generic entity
	 * @param entity
	 * @return
	 */
	public T persist(T entity) throws DAOException {
		getEntityManager().persist(entity);
		return entity;
	}

	/**
	 * Method to edit a generic entity
	 * @param entity
	 * @return
	 */
	public T edit(T entity) throws DAOException {
		getEntityManager().merge(entity);
		return entity;
	}

	/**
	 * Method to delete a generic entity
	 * @param entity
	 * @return
	 */
	public void remove(T entity) throws DAOException{
		getEntityManager().remove(entity);
	}

	/**
	 * Method to return all elements of a generic entity
	 * @return
	 */
	public List<T> findAll() throws DAOException{

		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	/**
	 * Method to find a generic entity by id
	 * @param id
	 * @return
	 */
	public T find(Long id) throws DAOException{
		T e = getEntityManager().find(entityClass, id);
		return e;
	}

}
