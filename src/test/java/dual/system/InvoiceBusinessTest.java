package dual.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import Business.BusinessException;
import Business.InvoiceBusiness;
import DAO.DAOException;
import DAO.InvoiceDAO;
import Entity.Invoice;
import Entity.InvoiceItem;

/**
 * Test Class responsible for ensuring the coverage of tests related to the  
 * methods present in class {@link InvoiceBusiness}.
 * 
 * @author bruno
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceBusinessTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock(name = "dao")
	private InvoiceDAO dao;

	@Spy
	@InjectMocks
	private InvoiceBusiness business = new InvoiceBusiness();

	@Before
	public void init() throws Exception {
	}

	/**
	 * Unit test to validate the successful execution of the persist method
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_success() throws BusinessException {
		Invoice invoiceCreated = new Invoice();
		invoiceCreated.setId(1l);

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		invoice.setDueDate(new Date());
		invoice.setInvoiceItens(Arrays.asList(new InvoiceItem()));

		Mockito.doReturn(invoiceCreated).when(dao).persist(invoice);

		Invoice result = business.persist(invoice);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), invoiceCreated.getId());
	}

	/**
	 * Unit test to validate whether the consumer's name is not null during
	 * persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_CustomerName_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Customer Name is Mandatory");

		Invoice invoice = new Invoice();
		business.persist(invoice);

	}

	/**
	 * Unit test to validate whether the issue date is not null during persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_IssueDate_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Issue Date is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		business.persist(invoice);
	}

	/**
	 * Unit test to validate whether the due date is not null during persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_DueDate_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Due Date is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		business.persist(invoice);
	}

	/**
	 * Unit test to validate whether invoice items are not null during persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_InvoiceItems_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("One or more invoice items is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		invoice.setDueDate(new Date());
		business.persist(invoice);
	}

	/**
	 * Unit test to validate whether invoice items list are empty during persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_InvoiceItems_empty() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("One or more invoice items is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		invoice.setDueDate(new Date());
		invoice.setInvoiceItens(new ArrayList<InvoiceItem>());
		business.persist(invoice);
	}

	/**
	 * Unit test to validate the successful execution of the edit method
	 * 
	 * @throws BusinessException
	 * @throws DAOException
	 */
	@Test
	public void edit_success() throws BusinessException, DAOException {
		Invoice invoiceCreated = new Invoice();
		invoiceCreated.setId(1l);

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		invoice.setDueDate(new Date());
		invoice.setInvoiceItens(Arrays.asList(new InvoiceItem()));

		Mockito.doReturn(invoiceCreated).when(dao).edit(invoice);

		Invoice result = business.edit(invoice);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), invoiceCreated.getId());
	}

	/**
	 * Unit test to validate whether the consumer's name is not null during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_CustomerName_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Customer Name is Mandatory");

		Invoice invoice = new Invoice();
		business.edit(invoice);

	}

	/**
	 * Unit test to validate whether the issue date is not null during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_IssueDate_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Issue Date is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		business.edit(invoice);
	}

	/**
	 * Unit test to validate whether the due date is not null during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_DueDate_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Due Date is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		business.edit(invoice);
	}

	/**
	 * Unit test to validate whether invoice items are not null during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_InvoiceItems_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("One or more invoice items is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		invoice.setDueDate(new Date());
		business.edit(invoice);
	}

	/**
	 * Unit test to validate whether invoice items list are empty during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_InvoiceItems_empty() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("One or more invoice items is Mandatory");

		Invoice invoice = new Invoice();
		invoice.setCustomerName("Unit test");
		invoice.setIssueDate(new Date());
		invoice.setDueDate(new Date());
		invoice.setInvoiceItens(new ArrayList<InvoiceItem>());
		business.edit(invoice);
	}
}
