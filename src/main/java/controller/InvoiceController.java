package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.rpc.ServiceException;

import org.core4j.Enumerable;
import org.core4j.xml.XDocument;
import org.core4j.xml.XElement;
import org.tempuri.MNBArfolyamServiceSoapImplLocator;

import Business.BusinessException;
import Business.InvoiceBusiness;
import Business.InvoiceItemBusiness;
import Entity.Invoice;
import Entity.InvoiceItem;
import hu.mnb.www.webservices.MNBArfolyamServiceSoap;
import hu.mnb.www.webservices.MNBArfolyamServiceSoap_GetCurrentExchangeRates_StringFault_FaultMessage;

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
	 * Bean of Invoice Business
	 */
	@Inject
	private InvoiceBusiness invoiceBusiness;
	/**
	 * Bean of InvoiceItem Business
	 */
	@Inject
	private InvoiceItemBusiness invoiceItemBusiness;
	private Invoice invoiceDetail;
	private Invoice newInvoice;
	private InvoiceItem newInvoiceItem;
	private Invoice editInvoice;
	private double currentEurValue = 0;

	public InvoiceController() throws MNBArfolyamServiceSoap_GetCurrentExchangeRates_StringFault_FaultMessage, RemoteException, ServiceException {
		this.newInvoice = new Invoice();
		this.newInvoiceItem = new InvoiceItem();
		this.currentEurValue = getEURValue();
	}

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Being destroied!!");
	}

	/**
	 * Method responsible for accessing the SOAP service and retrieving the exchange
	 * between HUF and EUR
	 * 
	 * @return HUF value for 1 EUR
	 * @throws MNBArfolyamServiceSoap_GetCurrentExchangeRates_StringFault_FaultMessage
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	public double getEURValue() throws MNBArfolyamServiceSoap_GetCurrentExchangeRates_StringFault_FaultMessage,
			RemoteException, ServiceException {
		MNBArfolyamServiceSoapImplLocator clnt = new MNBArfolyamServiceSoapImplLocator();
		MNBArfolyamServiceSoap impl = clnt.getCustomBinding_MNBArfolyamServiceSoap();
		String result = impl.getCurrentExchangeRates();
		XDocument xdoc = XDocument.parse(result);
		Enumerable<XElement> rate = xdoc.descendants("Rate");
		double eur = 0;
		for (XElement element : rate) {
			if (element.attribute("curr").getValue().equals("EUR")) {
				eur = Double.parseDouble(element.getValue().replace(",", "."));
			}
		}
		return eur;
	}

	/**
	 * Method to read All Invoices
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Invoice> readAllInvoices() throws BusinessException {
		try {
			List<Invoice> invoices = invoiceBusiness.findAll();
			for (Invoice invoice : invoices) {
				invoice.setTotalEur(invoice.getTotalValue() / this.currentEurValue);
			}
			return invoices;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Method to delete an invoice
	 * 
	 * @param invoice
	 * @throws BusinessException
	 */
	public void deleteInvoice(Invoice invoice) throws BusinessException {
		try {
			invoiceBusiness.remove(invoice);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Method to get invoice details
	 * 
	 * @param invoiceExample
	 */
	public void getDetails(Invoice invoiceExample) {
		this.invoiceDetail = invoiceExample;
		for (InvoiceItem item : this.invoiceDetail.getInvoiceItens()) {
			item.setTotalEur(item.getTotalItem() / this.currentEurValue);
		}
	}

	/**
	 * Method to set the invoice to be edited
	 * 
	 * @param invoice
	 */
	public void editInvoice(Invoice invoice) {
		this.editInvoice = invoice;
		for (InvoiceItem item : this.editInvoice.getInvoiceItens()) {
			item.setTotalEur(item.getTotalItem() / this.currentEurValue);
		}
	}

	public void saveInvoice() throws BusinessException {
		double total = 0;
		for (InvoiceItem item : this.editInvoice.getInvoiceItens()) {
			total = total + item.getTotalItem();
		}
		this.editInvoice.setTotalValue(total);
		this.invoiceBusiness.edit(this.editInvoice);
	}

	/**
	 * Method to create a new invoice
	 * 
	 * @throws BusinessException
	 */
	public void createInvoice() throws BusinessException {
		double total = 0;
		for (InvoiceItem item : this.newInvoice.getInvoiceItens()) {
			total = total + item.getTotalItem();
		}
		this.newInvoice.setTotalValue(total);
		this.newInvoice.setTotalEur(this.newInvoiceItem.getTotalItem() / this.currentEurValue);
		invoiceBusiness.persist(this.newInvoice);
	}

	/**
	 * Method to add a new invoice item to the new invoice
	 */
	public void addInvoiceItem() {
		this.newInvoiceItem.setTotalItem(this.newInvoiceItem.getQuantity() * this.getNewInvoiceItem().getUnitPrice());
		this.newInvoiceItem.setTotalEur(this.newInvoiceItem.getTotalItem() / this.currentEurValue);
		this.newInvoice.getInvoiceItens().add(newInvoiceItem);
		this.newInvoiceItem = new InvoiceItem();
	}

	/**
	 * Method to add a new invoice item to the edit invoice
	 * 
	 * @throws BusinessException
	 */
	public void addInvoiceItemEdit() throws BusinessException {
		this.newInvoiceItem.setTotalItem(this.newInvoiceItem.getQuantity() * this.getNewInvoiceItem().getUnitPrice());
		this.newInvoiceItem.setTotalEur(this.newInvoiceItem.getTotalItem() / this.currentEurValue);
		this.newInvoiceItem.setInvoice(this.editInvoice);
		this.invoiceItemBusiness.persist(newInvoiceItem);
		this.editInvoice.getInvoiceItens().add(newInvoiceItem);
		this.newInvoiceItem = new InvoiceItem();
	}


	/**
	 * Method to instantiate a new invoice
	 */
	public void instantiateNewInvoice() {
		this.newInvoice = new Invoice();
		this.newInvoice.setInvoiceItens(new ArrayList<InvoiceItem>());
	}

	/**
	 * Method to instantiate a new invoice item
	 */
	public void instantiateNewInvoiceItem() {
		// this.newInvoiceItem = new InvoiceItem();
	}

	/**
	 * Method responsible for retrieving invoiceBusiness property
	 * 
	 * @return Returns the invoiceBusiness property.
	 */
	public InvoiceBusiness getInvoiceBusiness() {
		return invoiceBusiness;
	}

	/**
	 * Method responsible for changing the invoiceDAO property.
	 * 
	 * @param invoiceDAO
	 */
	public void setInvoiceBusiness(InvoiceBusiness invoiceBusiness) {
		this.invoiceBusiness = invoiceBusiness;
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

	/**
	 * Method responsible for retrieving newInvoice property
	 * 
	 * @return Returns the newInvoice property.
	 */
	public Invoice getNewInvoice() {
		return newInvoice;
	}

	/**
	 * Method responsible for changing the newInvoice property.
	 * 
	 * @param newInvoice
	 */
	public void setNewInvoice(Invoice newInvoice) {
		this.newInvoice = newInvoice;
	}

	/**
	 * Method responsible for retrieving newInvoiceItem property
	 * 
	 * @return Returns the newInvoiceItem property.
	 */
	public InvoiceItem getNewInvoiceItem() {
		return newInvoiceItem;
	}

	/**
	 * Method responsible for changing the newInvoiceItem property.
	 * 
	 * @param newInvoiceItem
	 */
	public void setNewInvoiceItem(InvoiceItem newInvoiceItem) {
		this.newInvoiceItem = newInvoiceItem;
	}

	/**
	 * Method responsible for retrieving editInvoice property
	 * 
	 * @return Returns the editInvoice property.
	 */
	public Invoice getEditInvoice() {
		return editInvoice;
	}

	/**
	 * Method responsible for changing the editInvoice property.
	 * 
	 * @param editInvoice
	 */
	public void setEditInvoice(Invoice editInvoice) {
		this.editInvoice = editInvoice;
	}

	/**
	 * Method responsible for retrieving invoiceItemBusiness property
	 * 
	 * @return Returns the invoiceItemBusiness property.
	 */
	public InvoiceItemBusiness getInvoiceItemBusiness() {
		return invoiceItemBusiness;
	}

	/**
	 * Method responsible for changing the invoiceItemBusiness property.
	 * 
	 * @param invoiceItemBusiness
	 */
	public void setInvoiceItemBusiness(InvoiceItemBusiness invoiceItemBusiness) {
		this.invoiceItemBusiness = invoiceItemBusiness;
	}

}
