package dual.system;

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
import Business.InvoiceItemBusiness;
import DAO.DAOException;
import DAO.InvoiceItemDAO;
import Entity.InvoiceItem;
/**
 * Test Class responsible for ensuring the coverage of tests related to the  
 * methods present in class {@link InvoiceItemBusiness}.
 * @author bruno
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceItemTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock(name = "dao")
	private InvoiceItemDAO dao;

	@Spy
	@InjectMocks
	private InvoiceItemBusiness business = new InvoiceItemBusiness();

	@Before
	public void init() throws Exception {
	}
	/**
	 * Unit test to validate the successful execution of the persist method
	 * 
	 * @throws BusinessException
	 * @throws DAOException 
	 */
	@Test
	public void persist_success() throws BusinessException, DAOException {
		InvoiceItem invoiceItemCreated = new InvoiceItem();
		invoiceItemCreated.setId(1l);

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName("Unit test");
		invoiceItem.setUnitPrice(1.0);
		invoiceItem.setQuantity(2);

		Mockito.doReturn(invoiceItemCreated).when(dao).persist(invoiceItem);

		InvoiceItem result = business.persist(invoiceItem);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), invoiceItemCreated.getId());
	}

	/**
	 * Unit test to validate whether the product's name is not null during
	 * persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_ProductName_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Product Name is Mandatory");

		InvoiceItem invoice = new InvoiceItem();
		business.persist(invoice);

	}

	/**
	 * Unit test to validate whether the issue date is not null during persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_UnitPrice_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Unit price is Mandatory");

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName("Unit test");
		business.persist(invoiceItem);
	}

	/**
	 * Unit test to validate whether the quantity is not null during persistence
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void persist_Quantity_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Quantity is Mandatory");

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName("Unit test");
		invoiceItem.setUnitPrice(1.0);
		business.persist(invoiceItem);
	}


	/**
	 * Unit test to validate the successful execution of the edit method
	 * 
	 * @throws BusinessException
	 * @throws DAOException 
	 */
	@Test
	public void edit_success() throws BusinessException, DAOException {
		InvoiceItem invoiceItemCreated = new InvoiceItem();
		invoiceItemCreated.setId(1l);

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName("Unit test");
		invoiceItem.setUnitPrice(1.0);
		invoiceItem.setQuantity(2);

		Mockito.doReturn(invoiceItemCreated).when(dao).edit(invoiceItem);

		InvoiceItem result = business.edit(invoiceItem);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), invoiceItemCreated.getId());
	}

	/**
	 * Unit test to validate whether the product's name is not null during
	 * edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_ProductName_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Product Name is Mandatory");

		InvoiceItem invoice = new InvoiceItem();
		business.edit(invoice);

	}

	/**
	 * Unit test to validate whether the issue date is not null during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_UnitPrice_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Unit price is Mandatory");

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName("Unit test");
		business.edit(invoiceItem);
	}

	/**
	 * Unit test to validate whether the quantity is not null during edition
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void edit_Quantity_null() throws BusinessException {
		exception.expect(BusinessException.class);
		exception.expectMessage("Quantity is Mandatory");

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setName("Unit test");
		invoiceItem.setUnitPrice(1.0);
		business.edit(invoiceItem);
	}
}
