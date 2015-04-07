/**
 * @author Eswharreddy
 */
package com.main.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.main.beans.OfferBean;

public class OfferExtractor implements ResultSetExtractor<OfferBean> {
	@Override
	public OfferBean extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		OfferBean offer = new OfferBean();
		offer.setPropertyId(rs.getInt("propertyid"));
//		offer.setAmount(rs.get("amount"));
//		offer.setDate(rs.getInt("date"));
	
		return offer;

	}

}




