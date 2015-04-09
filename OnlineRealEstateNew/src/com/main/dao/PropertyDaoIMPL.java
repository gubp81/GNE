/**
 * @author Eswharreddy
 */
package com.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.main.beans.PropertyBean;
import com.main.util.PropertyExtractor;
import com.main.beans.OfferBean;
import com.main.util.OfferExtractor;

public class PropertyDaoIMPL extends JdbcDaoSupport implements PropertyDao{
	PropertyDaoIMPL()
	{

	}
	@Autowired
	public PropertyDaoIMPL(DataSource dataSource) {
		setDataSource(dataSource);
	}
	//	@Override
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
			){

		// Requirement to only show sold properties in past 15 days		
		String sql="select * from \"Property\" where ( sold is FALSE"
				+  " or now()<=solddate+15)";
		System.out.println("SQL: "+sql);

		if(!proptype.equals("Click to Select"))
			sql+=" and proptype='"+proptype+"'";
		if(!size.equals("Click to Select"))
			sql += " and size='"+size+"'";

		//Price ranges
		if(!price.equals("Click to Select")){
			if(price.startsWith(String.valueOf('1')))
				sql += " and price<'100000'";
			if(price.startsWith(String.valueOf('2')))
				sql += " and price>='100000' and price<'200000'";
			if(price.startsWith(String.valueOf('3')))
				sql += " and price>='200000' and price<'300000'";
			if(price.startsWith(String.valueOf('4')))
				sql += " and price>='300000' and price<'400000'";
			if(price.startsWith(String.valueOf('5')))
				sql += " and price>='400000'";
		}
		if(garage)
			sql+=" and garage='"+garage+"'";
		if(pool)
			sql+=" and pool='"+pool+"'";
		if(ac)
			sql+=" and ac='"+ac+"'";		
		if(!region.equals("Click to Select"))
			sql += " and region='"+region+"'";
		if(school)
			sql+=" and school='"+school+"'";
		if(metro)
			sql+=" and metro='"+metro+"'";
		if(hospital)
			sql+=" and hospital='"+hospital+"'";
		if(shopping_mall)
			sql+=" and shopping_mall='"+shopping_mall+"'";

		if(sort.equals("by Price: low to high")){
			sql+=" order by price asc";
		}
		else if(sort.equals("by Price: high to low")){
			sql+=" order by price desc";
		}
		else if(sort.equals("by PostDate: recently posted first")){
			sql+=" order by postdate desc";
		}			
		else if(sort.equals("by PostDate: oldest first")){
			sql+=" order by postdate asc";			
		}
		List<PropertyBean> list = getJdbcTemplate().query(sql, new PropertyRowMapper());
		return list;
	}

	@Override
	public PropertyBean getDetails(int propertyid){
		PropertyBean property= new PropertyBean();
		String sql = "select * from \"Property\" where propertyid="+propertyid;
		property = getJdbcTemplate().queryForObject(sql, new PropertyRowMapper());
		return property;
	}

	@Override
	public String getSellersEmail(int propertyid){
		String sql = "select email from \"User\" INNER JOIN \"Property\" ON (\"User\".userid =  \"Property\".seller) where \"Property\".propertyid="+propertyid;
		String email = (String) getJdbcTemplate().queryForObject(sql,String.class);
		return email;
	}

	@Override
	public boolean makeanOffer(int propertyid,
			String name,
			String phone,
			String email,			
			String amount
			){
		int buyer = 0, row = 0;
		String sql, sqlUser;

		sqlUser="Select userid from \"User\" where email ='"+email+"'";
		System.out.println("SQL: "+sqlUser);

		try {
			buyer=getJdbcTemplate().queryForInt(sqlUser);			
		} catch (DataAccessException e){

		}
		if(buyer==0){
			System.out.println("User not found");	
			sql="Insert into \"User\" ( name, phone, email, type) VALUES ('"+name+"','"+phone+"','"+email+"',1)";
			System.out.println("SQL: "+sql);
			try {
				buyer=getJdbcTemplate().update(sql);
				buyer=getJdbcTemplate().queryForInt(sqlUser);
			} catch (DataAccessException e1) {
				e1.printStackTrace();
			}				
		}
		System.out.println("User found: "+ buyer);	

		sql="Insert into \"Offer\" ( buyerid, propertyid, amount, date, isaccepted, isrejected) VALUES ('"+buyer+"','"+propertyid+"','"+amount+"',now(), false, false)";
		System.out.println("SQL: "+sql);	
		try {
			row=getJdbcTemplate().update(sql);
		} catch (DataAccessException e){	
		}
		if(row==1){
			//Increase number of offers for this Property
			sql="Update \"Property\" set offersmade = offersmade + 1 where propertyid = "+propertyid;
			row=getJdbcTemplate().update(sql);
			if(row==1)
				System.out.println("Offer successfully posted");
			return true;
		}
		return false;
	}

	@Override  	
	public List<OfferBean> listOffers(int propertyid){
		System.out.println("Offers");
		//Requirement: Offers are only valid within 3 days
		String sql = "select * from \"Offer\" INNER JOIN \"User\" ON (\"Offer\".buyerid = \"User\".userid) where \"Offer\".propertyid="+propertyid+"and now()<=date+3 and isaccepted = false and isrejected = false";
		System.out.println("SQL: "+sql);	
		List<OfferBean> offers = getJdbcTemplate().query(sql, new OfferRowMapper());
		System.out.println("OfferRowMapper OK");
		return offers;
	}


	@Override
	public OfferBean offerDecision(int propertyid, int buyerid, String decision){
		int row = 0;        
		String sql;
		OfferBean offer= new OfferBean();
		try {
			if(decision.contains("Accept")){
				sql="Update \"Offer\" set isaccepted = true where propertyid = "+propertyid+" and buyerid = "+buyerid;
				row=getJdbcTemplate().update(sql);
				if(row==1){
					sql = "select * from \"Offer\" INNER JOIN \"User\" ON (\"Offer\".buyerid = \"User\".userid) where \"Offer\".propertyid="+propertyid+" and buyerid = "+buyerid;
					offer = getJdbcTemplate().queryForObject(sql, new OfferRowMapper());

					sql="Update \"Property\" set solddate = now(), sold = true where propertyid = "+propertyid;
					row=getJdbcTemplate().update(sql);
					offer.isaccepted = true;
				}	
			}
			else if(decision.contains("Reject")){
				sql="Update \"Offer\" set isrejected = true where propertyid = "+propertyid+" and buyerid = "+buyerid;
				row=getJdbcTemplate().update(sql);	
				offer.isrejected = true;
			}
		}
		catch (DataAccessException e){	
		}
		return offer;
	}


}

class PropertyRowMapper implements RowMapper<PropertyBean>{

	@Override  
	public PropertyBean mapRow(ResultSet resultSet, int line) throws SQLException {   
		PropertyExtractor userExtractor = new PropertyExtractor();   
		return userExtractor.extractData(resultSet);   
	} 
}

class OfferRowMapper implements RowMapper<OfferBean>{

	@Override  
	public OfferBean mapRow(ResultSet resultSet, int line) throws SQLException {   
		OfferExtractor userExtractor = new OfferExtractor();   
		return userExtractor.extractData(resultSet);   
	} 
}
