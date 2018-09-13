package Business;

import javax.ejb.Local;
import javax.ejb.Stateless;

import DAO.DAOException;
import DAO.InvoiceDAO;
import Entity.Invoice;

@Stateless
@Local
public class InvoiceBusiness extends GenericBusiness<Invoice, InvoiceDAO> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see Business.GenericBusiness#persist(java.lang.Object)
	 */
	@Override
	public Invoice persist(Invoice entity) throws BusinessException {

		if (entity.getCustomerName() == null) {
			throw new BusinessException("Customer Name is Mandatory");
		} else if (entity.getIssueDate() == null) {
			throw new BusinessException("Issue Date is Mandatory");
		} else if (entity.getDueDate() == null) {
			throw new BusinessException("Due Date is Mandatory");
		} else if (entity.getInvoiceItens() == null || entity.getInvoiceItens().size() == 0) {
			throw new BusinessException("One or more invoice items is Mandatory");
		}
		return getDAO().persist(entity);
	}
	/*
	 * (non-Javadoc)
	 * @see Business.GenericBusiness#edit(java.lang.Object)
	 */
	@Override
	public Invoice edit(Invoice entity) throws BusinessException {

		if (entity.getCustomerName() == null) {
			throw new BusinessException("Customer Name is Mandatory");
		} else if (entity.getIssueDate() == null) {
			throw new BusinessException("Issue Date is Mandatory");
		} else if (entity.getDueDate() == null) {
			throw new BusinessException("Due Date is Mandatory");
		} else if (entity.getInvoiceItens() == null || entity.getInvoiceItens().size() == 0) {
			throw new BusinessException("One or more invoice items is Mandatory");
		}
		try {
			return getDAO().edit(entity);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
}
