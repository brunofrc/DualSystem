package DAO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entity.Invoice;
import Entity.InvoiceItem;
/**
 * Database access class, where specific methods for manipulating objects of the
 * {@link InvoiceItem} type are implemented.
 * 
 * @author bruno
 *
 */
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.GenericDAO#remove(java.lang.Object)
	 */
	@Override
	public void remove(InvoiceItem invoiceitem) {
		Invoice invoice = invoiceitem.getInvoice();
    	invoice.getInvoiceItens().remove(invoiceitem);
    	getEntityManager().flush();
		getEntityManager().remove(getEntityManager().merge(invoice));
	}

}
