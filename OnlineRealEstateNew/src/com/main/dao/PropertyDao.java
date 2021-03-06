/**
 * Property Data Object Mapping
 */
package com.main.dao;
import java.util.List;

import com.main.beans.*;

public interface PropertyDao {

	public List<PropertyBean> searchAny(
			String proptype,
			String size,
			String price,
			String region,
			String sort,
			boolean garage,
			boolean pool,
			boolean ac,
			boolean school,
			boolean metro,
			boolean hospital,
			boolean shopping_mall
			);

	public PropertyBean getDetails(int propertyid);

	public String getSellersEmail(int propertyid);

	public boolean makeanOffer(int propertyid,
			String name,
			String phone,
			String email,
			String amount
			);
	public List<OfferBean> listOffers(int propertyid);

	public OfferBean offerDecision(int propertyid, int buyerid, String decision);

	public int post(
			String name,
			String phone,
			String email,
			String proptype,
			String size,
			int price,
			String address,
			String region,
			int year,
			String description,
			boolean garage,
			boolean pool,
			boolean ac,
			boolean school,
			boolean metro,
			boolean hospital,
			boolean shopping_mall
			);
	
	public void remove( int propertyid );

}
