package DAO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entity.Invoice;
import Entity.InvoiceItem;

/**
 * Database access class, where specific methods for manipulating objects of the
 * {@link Invoice} type are implemented.
 * 
 * @author bruno
 *
 */
@Stateless
@Local
public class InvoiceDAO extends GenericDAO<Invoice> {
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

	@Override
	public Invoice persist(Invoice invoice) {
		for(InvoiceItem item : invoice.getInvoiceItens()) {
			item.setInvoice(invoice);
		}
		getEntityManager().persist(invoice);
		return invoice;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.GenericDAO#remove(java.lang.Object)
	 */
	@Override
	public void remove(Invoice invoice) {
		for (InvoiceItem item : invoice.getInvoiceItens()) {
			invoice.getInvoiceItens().remove(item);
			getEntityManager().flush();
		}
		getEntityManager().remove(getEntityManager().merge(invoice));
	}
}
