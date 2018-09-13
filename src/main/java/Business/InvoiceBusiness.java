package Business;


import javax.ejb.Local;
import javax.ejb.Stateless;
import DAO.InvoiceDAO;
import Entity.Invoice;
@Stateless
@Local
public class InvoiceBusiness extends GenericBusiness<Invoice,InvoiceDAO> {

	
}
