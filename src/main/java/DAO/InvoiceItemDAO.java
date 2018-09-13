package DAO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entity.InvoiceItem;

@Stateless
@Local
public class InvoiceItemDAO extends GenericDAO<InvoiceItem>{

	/**
	 * Builder
	 */
	public InvoiceItemDAO() {
		super(InvoiceItem.class);
	}

	@PersistenceContext(unitName = "PostGresDS")
	private EntityManager em;

	/**
	 * Builder with entityClass
	 * 
	 * @param entityClass
	 */
	public InvoiceItemDAO(Class<InvoiceItem> entityClass) {
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
