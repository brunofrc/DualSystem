package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Class responsible for ensuring the mapping of the relational object
 * 
 * @author bruno
 *
 */
@Entity
@Table(name = "TB_INVOICE")
@SequenceGenerator(name = "SEQ_INVOICE", sequenceName = "SEQ_INVOICE", allocationSize = 1)
public class Invoice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVOICE")
	@Column(name = "ID_INVOICE")
	private Long id;

	@Column(name = "CUSTUMER_NAME", length = 60, nullable = false)
	private String customerName;

	@OneToMany(mappedBy = "invoice", targetEntity = InvoiceItem.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<InvoiceItem> invoiceItens;

	@Temporal(TemporalType.DATE)
	@Column(name = "ISSUE_DATE", nullable = false)
	private Date issueDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", nullable = false)
	private Date dueDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "PAY_DATE", nullable = true)
	private Date payDate;

	@Column(name = "COMMENT", length = 120, nullable = true)
	private String comments;
	
	@Column(name = "TOTALVALUE")
	private Double totalValue;
	
	@Transient
	private Double totalEur;

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
	 * Method responsible for retrieving customerName property
	 * 
	 * @return Returns the customerName property.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Method responsible for changing the customerName property.
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Method responsible for retrieving issueDate property
	 * 
	 * @return Returns the issueDate property.
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * Method responsible for changing the issueDate property.
	 * 
	 * @param issueDate
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Method responsible for retrieving dueDate property
	 * 
	 * @return Returns the dueDate property.
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Method responsible for changing the dueDate property.
	 * 
	 * @param dueDate
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Method responsible for retrieving payDate property
	 * 
	 * @return Returns the payDate property.
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * Method responsible for changing the payDate property.
	 * 
	 * @param payDate
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * Method responsible for retrieving comments property
	 * 
	 * @return Returns the comments property.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Method responsible for changing the comments property.
	 * 
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Method responsible for retrieving totalValue property
	 * 
	 * @return Returns the totalValue property.
	 */
	public Double getTotalValue() {
		return totalValue;
	}

	/**
	 * Method responsible for changing the totalValue property.
	 * 
	 * @param totalValue
	 */
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * Method responsible for retrieving invoiceItens property
	 * 
	 * @return Returns the invoiceItens property.
	 */
	public List<InvoiceItem> getInvoiceItens() {
		return invoiceItens;
	}

	/**
	 * Method responsible for changing the invoiceItens property.
	 * 
	 * @param invoiceItens
	 */
	public void setInvoiceItens(List<InvoiceItem> invoiceItens) {
		this.invoiceItens = invoiceItens;
	}
	/**
	 * Method responsible for retrieving totalEur property
	 * 
	 * @return Returns the totalEur property.
	 */
	public Double getTotalEur() {
		return totalEur;
	}
	/**
	 * Method responsible for changing the totalEur property.
	 * 
	 * @param totalEur
	 */
	public void setTotalEur(Double totalEur) {
		this.totalEur = totalEur;
	}

}
