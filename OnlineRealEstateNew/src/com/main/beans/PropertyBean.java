/**
 * @author Eswarreddy
 */
package com.main.beans;

import java.util.Date;

public class PropertyBean {
	private int propertyid;
	private String soldValue;	
	private String address;
	private byte[] image;
	private Date postDate;
	private int offers;
	private String type;
	private int size;
	private String price;
	private String region;
	private boolean school;
	private boolean hospital;
	private boolean metro;
	private boolean shopping_mall;
	private String schoolValue;
	private String metroValue;
	private String hospitalValue;
	private String shoppingMallValue;
	private String description;
	private String encodedImage;

	
	private boolean garage;
	private boolean pool;
	private boolean ac;
	private String garageValue;
	private String poolValue;
	private String acValue;
	private int year;
	
	private boolean hideOffer;

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}	
	
	/**
	 * @return the propertyid
	 */
	public int getpropertyid() {
		return propertyid;
	}

	/**
	 * @return the propertyid
	 */
	public void setpropertyid(int propertyid) {
		this.propertyid = propertyid;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public String getRegion() {

		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public boolean getGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}

	public boolean getPool() {
		return pool;
	}
	public void setPool(boolean pool) {
		this.pool = pool;
	}
	
	public boolean getAc() {
		return ac;
	}
	public void setAc(boolean ac) {
		this.ac = ac;
	}
	
	public boolean getSchool() {
		return school;
	}
	public void setSchool(boolean school) {
		this.school = school;
	}
	
	public boolean isHospital() {
		return hospital;
	}
	public void setHospital(boolean hospital) {
		this.hospital = hospital;
	}
	/**
	 * @return the metro
	 */
	public boolean isMetro() {
		return metro;
	}
	/**
	 * @param metro the metro to set
	 */
	public void setMetro(boolean metro) {
		this.metro = metro;
	}
	/**
	 * @return the shopping_mall
	 */
	public boolean isShopping_mall() {
		return shopping_mall;
	}
	/**
	 * @param shopping_mall the shopping_mall to set
	 */
	public void setShopping_mall(boolean shopping_mall) {
		this.shopping_mall = shopping_mall;

	}
	/**
	 * @return the garageValue
	 */
	public String getGarageValue() {
		return garageValue;
	}
	/**
	 * @param garageValue the garageValue to set
	 */
	public void setGarageValue(String garageValue) {
		this.garageValue = garageValue;
	}

	/**
	 * @return the poolValue
	 */
	public String getPoolValue() {
		return poolValue;
	}
	/**
	 * @param poolValue the poolValue to set
	 */
	public void setPoolValue(String poolValue) {
		this.poolValue = poolValue;
	}
	
	/**
	 * @return the acValue
	 */
	public String getAcValue() {
		return acValue;
	}
	/**
	 * @param acValue the acValue to set
	 */
	public void setacValue(String acValue) {
		this.acValue = acValue;
	}
	
	/**
	 * @return the schoolValue
	 */
	public String getSchoolValue() {
		return schoolValue;
	}
	
	/**
	 * @param schoolValue the schoolValue to set
	 */
	public void setSchoolValue(String schoolValue) {
		this.schoolValue = schoolValue;
	}
	/**
	 * @return the metroValue
	 */
	public String getMetroValue() {
		return metroValue;
	}
	/**
	 * @param metroValue the metroValue to set
	 */
	public void setMetroValue(String metroValue) {
		this.metroValue = metroValue;
	}
	/**
	 * @return the hospitalValue
	 */
	public String getHospitalValue() {
		return hospitalValue;
	}
	/**
	 * @param hospitalValue the hospitalValue to set
	 */
	public void setHospitalValue(String hospitalValue) {
		this.hospitalValue = hospitalValue;
	}
	/**
	 * @return the shoppingMallValue
	 */
	public String getShoppingMallValue() {
		return shoppingMallValue;
	}
	/**
	 * @param shoppingMallValue the shoppingMallValue to set
	 */
	public void setShoppingMallValue(String shoppingMallValue) {
		this.shoppingMallValue = shoppingMallValue;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the image
	 */
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param inputStream the image to set
	 */
	public void setImage(byte[] bs) {
		this.image = bs;
	}
	/**
	 * @return the encodedImage
	 */
	public String getEncodedImage() {
		return encodedImage;
	}
	/**
	 * @param encodedImage the encodedImage to set
	 */
	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}
	/**
	 * @return the postDate
	 */
	public Date getPostDate() {
		return postDate;
	}
	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	/**
	 * @return the offers
	 */
	public int getOffers() {
		return offers;
	}
	/**
	 * @param offers the offers to set
	 */
	public void setOffers(int offers) {
		this.offers = offers;
	}

	/**
	 * @return the soldValue
	 */
	public String getSoldValue() {
		return soldValue;
	}
	/**
	 * @param soldValue the soldValue to set
	 */
	public void setSoldValue(String soldValue) {
		this.soldValue = soldValue;
	}	
	
}




