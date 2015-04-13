/**
 * @author Gustavo Pereira
 */
package com.main.beans;

import java.util.Date;

public class OfferBean {
	public int propertyid;
	public int buyerid;
	public String amount;	
	public Date date;	
	public String name;
	public String phone;
	public String email;
	public boolean isaccepted;
	public boolean isrejected;
	public boolean sold;
	
	/**
	 * @return the propertyid
	 */
	public int getpropertyid() {
		return propertyid;
	}

	/**
	 * @return the buyerid
	 */
	public int getbuyerid() {
		return buyerid;
	}

	/**
	 * @return the name
	 */
	public String getname() {
		return name;
	}
	/**
	 * @return the offer price
	 */
	public String getamount() {
		return amount;
	}
	
	/**
	 * @return the phone
	 */
	public String getphone() {
		return phone;
	}
	/**
	 * @return the email
	 */
	public String getemail() {
		return email;
	}

	/**
	 * @return the date
	 */
	public Date getdate() {
		return date;
	}

}




