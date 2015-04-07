/**
 * @author Eswharreddy
 */
package com.main.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.main.beans.PropertyBean;

public class PropertyExtractor implements ResultSetExtractor<PropertyBean> {
	@Override
	public PropertyBean extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		PropertyBean prop = new PropertyBean();
		prop.setpropertyid(rs.getInt("propertyid"));
		prop.setAddress(rs.getString("address"));
		prop.setPostDate(rs.getDate("postDate"));
		prop.setOffers(rs.getInt("offersMade"));
		prop.setType(rs.getString("propType"));
		prop.setSize(rs.getInt("size"));
		prop.setPrice(rs.getString("price"));
		prop.setRegion(rs.getString("region"));
		prop.setYear(rs.getInt("year"));			
		prop.setDescription(rs.getString("description"));

		
		if(rs.getBoolean("garage")){
			prop.setGarageValue("Garage");
		}

		if(rs.getBoolean("pool")){
			prop.setPoolValue("Pool");
		}

		if(rs.getBoolean("ac")){
			prop.setacValue("A/C");
		}
	
		if(rs.getBoolean("school")){
			prop.setSchoolValue("School");
		}
		prop.setMetro(rs.getBoolean("metro"));
		if(rs.getBoolean("metro")){
			prop.setMetroValue("Metro");
		}
		prop.setHospital(rs.getBoolean("hospital"));
		if(rs.getBoolean("hospital")){
			prop.setHospitalValue("Hospital");
		}
		prop.setShopping_mall(rs.getBoolean("shopping_mall"));
		if(rs.getBoolean("shopping_mall")){
			prop.setShoppingMallValue("Shopping Mall");
		}
	      if(rs.getBoolean("sold")){
	    	  prop.setSoldValue("Sold");
	      }		
		return prop;

	}

}




