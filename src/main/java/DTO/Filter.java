package DTO;

import java.util.Date;

public class Filter {
	private Date inicialDate;
	private Date finalDate;
	private String custumerName;
	public Date getIniDate() {
		return inicialDate;
	}
	public void setIniDate(Date iniDate) {
		this.inicialDate = iniDate;
	}
	public Date getFinDate() {
		return finalDate;
	}
	public void setFinDate(Date finDate) {
		this.finalDate = finDate;
	}
	public String getCusName() {
		return custumerName;
	}
	public void setCusName(String cusName) {
		this.custumerName = cusName;
	}
}
