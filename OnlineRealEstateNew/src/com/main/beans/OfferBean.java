/**
 * @author Gustavo Pereira
 */
package com.main.beans;

import java.util.Date;

public class OfferBean {
	private int propertyid;
	private String name;
	private String phone;
	private String email;
	private String amount;	
	private Date date;	
	/**
	 * @return the propertyid
	 */
	public int getPropertyId() {
		return propertyid;
	}

	/**
	 * @return the propertyid
	 */
	public void setPropertyId(int propertyid) {
		this.propertyid = propertyid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the offer price
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param i the offer price to set
	 */
	public void setAmount(String i) {
		this.amount = i;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phonenumber to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}




