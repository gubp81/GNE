/**
 * 
 */
package com.main.dao;
import java.util.List;

import com.main.beans.*;

/**
 * @author test
 *
 */
public interface PropertyDao {

	public List<PropertyBean> searchAny(
			String property_type,
			String size,
			String price,
			String region,
			String sort,
			boolean school,
			boolean metro,
			boolean hospital,
			boolean shopping_mall
			);
	
	public boolean offer( );
	
	public boolean makeanOffer(String name,
			String phone,
			String email,
			String amount
			);
		
}
