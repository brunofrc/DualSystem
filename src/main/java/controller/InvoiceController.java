package controller;

import java.rmi.RemoteException;
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
import org.hibernate.internal.util.xml.XmlDocument;
import org.tempuri.CustomBinding_MNBArfolyamServiceSoapStub;
import org.tempuri.MNBArfolyamServiceSoapImplLocator;

import Business.BusinessException;
import Business.InvoiceBusiness;
import DAO.DAOException;
import DAO.InvoiceDAO;
import Entity.Invoice;
import Entity.InvoiceItem;
import hu.mnb.www.webservices.MNBArfolyamServiceSoap;
import hu.mnb.www.webservices.MNBArfolyamServiceSoapProxy;
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
	 * Bean of Invoice DAO
	 */
	@Inject
	private InvoiceBusiness invoiceBusiness;

	private Invoice invoiceDetail;

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
			for(Invoice invoice : invoices) {
				invoice.setTotalEur(invoice.getTotalValue()/getEURValue());
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

}
