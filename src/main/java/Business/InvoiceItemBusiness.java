package Business;

import javax.ejb.Local;
import javax.ejb.Stateless;

import DAO.DAOException;
import DAO.InvoiceItemDAO;
import Entity.InvoiceItem;

/**
 * Business class, where specific methods are implemented to manipulation and
 * validation of objects of type @Link {InvoiceItem}.
 * 
 * @author bruno
 *
 */
@Stateless
@Local
public class InvoiceItemBusiness extends GenericBusiness<InvoiceItem, InvoiceItemDAO> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see Business.GenericBusiness#persist(java.lang.Object)
	 */
	@Override
	public InvoiceItem persist(InvoiceItem entity) throws BusinessException {

		if (entity.getName() == null) {
			throw new BusinessException("Product Name is Mandatory");
		} else if (entity.getUnitPrice() == 0) {
			throw new BusinessException("Unit price is Mandatory");
		} else if (entity.getQuantity() == 0) {
			throw new BusinessException("Quantity is Mandatory");
		}
		try {
			return getDAO().persist(entity);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Business.GenericBusiness#edit(java.lang.Object)
	 */
	@Override
	public InvoiceItem edit(InvoiceItem entity) throws BusinessException {

		if (entity.getName() == null) {
			throw new BusinessException("Product Name is Mandatory");
		} else if (entity.getUnitPrice() == 0) {
			throw new BusinessException("Unit price is Mandatory");
		} else if (entity.getQuantity() == 0) {
			throw new BusinessException("Quantity is Mandatory");
		}
		try {
			return getDAO().edit(entity);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
}
