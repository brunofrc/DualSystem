package DAO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entity.Invoice;

/**
 * Database access class, where specific methods for manipulating objects of the
 * {@link Invoice} type are implemented.
 * 
 * @author bruno
 *
 */
@Stateless
@Local
public class InvoiceDAO extends IGenericDAO<Invoice> {
	/**
	 * Builder
	 */
	public InvoiceDAO() {
		super(Invoice.class);
	}

	@PersistenceContext(unitName = "PostGresDS")
	private EntityManager em;

	/**
	 * Builder with entityClass
	 * 
	 * @param entityClass
	 */
	public InvoiceDAO(Class<Invoice> entityClass) {
		super(entityClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
