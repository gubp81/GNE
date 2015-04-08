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
		offer.buyerid=rs.getInt("buyerid");
		offer.propertyid=rs.getInt("propertyid");
		offer.amount=rs.getString("amount");
		offer.date=rs.getDate("date");
		offer.name=rs.getString("name");
		offer.phone=rs.getString("phone");
		offer.email=rs.getString("email");
		return offer;

	}

}




