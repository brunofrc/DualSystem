package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * Class responsible for ensuring the mapping of the relational object
 * 
 * @author bruno
 *
 */
@Entity
@Table(name = "TB_INVOICE_ITEM")
@SequenceGenerator(name = "SEQ_INVOICE_ITEM", sequenceName = "SEQ_INVOICE_ITEM", allocationSize = 1)
public class InvoiceItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVOICE_ITEM")
	@Column(name = "ID_INVOICE_ITEM")
	private Long id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Invoice.class)
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	@Column(name = "PRODUCT_NAME", nullable = false)
	private String name;

	@Column(name = "UNIT_PRICE", nullable = false)
	private double unitPrice;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "TOTALITEM")
	private double totalItem;

	@Transient
	private double totalEur;

	/**
	 * Method responsible for retrieving id property
	 * 
	 * @return Returns the id property.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Method responsible for changing the id property.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method responsible for retrieving name property
	 * 
	 * @return Returns the name property.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method responsible for changing the name property.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method responsible for retrieving totalEur property
	 * 
	 * @return Returns the totalEur property.
	 */
	public double getTotalEur() {
		return totalEur;
	}

	/**
	 * Method responsible for changing the totalEur property.
	 * 
	 * @param totalEur
	 */
	public void setTotalEur(double totalEur) {
		this.totalEur = totalEur;
	}

	/**
	 * Method responsible for retrieving invoice property
	 * 
	 * @return Returns the invoice property.
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Method responsible for changing the invoice property.
	 * 
	 * @param invoice
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * Method responsible for retrieving unitPrice property
	 * 
	 * @return Returns the unitPrice property.
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Method responsible for changing the unitPrice property.
	 * 
	 * @param unitPrice
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Method responsible for retrieving quantity property
	 * 
	 * @return Returns the quantity property.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Method responsible for changing the quantity property.
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Method responsible for retrieving totalItem property
	 * 
	 * @return Returns the totalItem property.
	 */
	public double getTotalItem() {
		return totalItem;
	}

	/**
	 * Method responsible for changing the totalItem property.
	 * 
	 * @param totalItem
	 */
	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
	}
}
