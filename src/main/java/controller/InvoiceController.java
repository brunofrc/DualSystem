package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import DAO.DAOException;
import DAO.InvoiceDAO;
import Entity.Invoice;
import Entity.InvoiceItem;

/**
 * Invoice Controller class
 * 
 * @author bruno
 *
 */
@Named
@ApplicationScoped
public class InvoiceController {
	/**
	 * Bean of Invoice DAO
	 */
	@Inject
	private InvoiceDAO invoiceDAO;

	private List<InvoiceItem> itensDetail;
	private Invoice invoiceDetail;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Being destroied!!");
	}

	/**
	 * Method to read All Invoices
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Invoice> readAllInvoices() throws DAOException  {
		try {
			return invoiceDAO.findAll();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Method to get invoice details
	 * 
	 * @param invoiceExample
	 */
	public void getDetails(Invoice invoiceExample) {
		this.invoiceDetail = invoiceExample;
		this.itensDetail = invoiceExample.getInvoiceItens();
	}

	/**
	 * Method responsible for changing the itensDetail property.
	 * 
	 * @param itensDetail
	 */
	public void setItensDetail(List<InvoiceItem> itensDetail) {
		this.itensDetail = itensDetail;
	}
	/**
	 * Method responsible for retrieving invoiceDAO property
	 * 
	 * @return Returns the invoiceDAO property.
	 */
	public InvoiceDAO getInvoiceDAO() {
		return invoiceDAO;
	}

	/**
	 * Method responsible for changing the invoiceDAO property.
	 * 
	 * @param invoiceDAO
	 */
	public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
		this.invoiceDAO = invoiceDAO;
	}
	/**
	 * Method responsible for retrieving invoiceDetail property
	 * 
	 * @return Returns the invoiceDetail property.
	 */
	public Invoice getInvoiceDetail() {
		return invoiceDetail;
	}

	/**
	 * Method responsible for changing the invoiceDetail property.
	 * 
	 * @param invoiceDetail
	 */
	public void setInvoiceDetail(Invoice invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

}
