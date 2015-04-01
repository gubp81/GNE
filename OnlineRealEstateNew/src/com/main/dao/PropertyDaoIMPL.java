/**
 * 
 */
package com.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.main.beans.PropertyBean;
import com.main.util.PropertyExtractor;

/**
 * @author Eswharreddy
 *
 */
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
			String propType,
			String size,
			String price,
			String region,
			String sort,
			boolean school,
			boolean metro,
			boolean hospital,
			boolean shopping_mall
			){

		// Requirement to only show sold properties in past 15 days		
		Calendar cal = Calendar.getInstance();
		cal.set(1, 2, 3);
		cal.add(3,-15);

		String sql="select * from \"Property\" where ( sold is FALSE"
				+  " or solddate::date > '"+cal.get(1)+"-"+cal.get(2)+"-"+cal.get(3)+"' )";
		System.out.println("SQL: "+sql);

		if(!propType.equals("Click to Select"))
			sql+=" and propType='"+propType+"'";
		if(!size.equals("Click to Select"))
			sql += " and size='"+size+"'";
		if(!price.equals("Click to Select")){
			if(price.startsWith(String.valueOf('1')))
			sql += " and price<'50000'";
			if(price.startsWith(String.valueOf('2')))
			sql += " and price>='50000' and price<'100000'";
			if(price.startsWith(String.valueOf('3')))
			sql += " and price>='100000' and price<'200000'";
			if(price.startsWith(String.valueOf('4')))
			sql += " and price>='200000' and price<'300000'";
			if(price.startsWith(String.valueOf('5')))
			sql += " and price>='300000'";
		}
		
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
		if(sort.equals("low to high")){
			sql+=" order by price asc";
		}
		else if(sort.equals("high to low")){
			sql+=" order by price desc";
		}
		List<PropertyBean> list = getJdbcTemplate().query(sql, new PropertyRowMapper());
		return list;
	}

	@Override
	public boolean makeanOffer(String name,
			String phone,
			String email,			
			String amount
			){
		boolean result = false;
		int buyer, property = 0;
		String sql;
		
		Calendar cal = Calendar.getInstance();
		cal.set(1, 2, 3);

		String getUser="Select userid from \"User\" where email ='"+email+"'";
		System.out.println("SQL: "+getUser);
		buyer=getJdbcTemplate().queryForInt(getUser);
		if (buyer==0) {
			System.out.println("User not found");	
			sql="Insert into \"User\" ( name, phone, email, type) VALUES ('"+name+"','"+phone+"','"+email+"',1)";
			System.out.println("SQL: "+sql);
			buyer=getJdbcTemplate().queryForInt(getUser);;			
		}
		System.out.println("User found: "+buyer);	
		
		sql= getUser="Select propertyid from \"Offer\" where buyerid ='"+buyer+"'";
		property = getJdbcTemplate().queryForInt(sql);
		
		if(property!=0){
			System.out.println("Buyer already posted offer to property: "+property);
	      return result;
		}
		sql="Insert into \"Offer\" ( buyerid, propertyid, amount, date) VALUES ('"+buyer+"','"+buyer+"','"+amount+"','"+cal.get(1)+"-"+cal.get(2)+"-"+cal.get(3)+"')";
		System.out.println("SQL: "+sql);
		int row=getJdbcTemplate().update(sql);
		if(row==1){
			sql="Update \"Property\" set offersmade = offersmade + 1 where propertyid = "+property;
			row=getJdbcTemplate().update(sql);
			if(row==1)
			result=true;
		}
		return result;
	}
	@Override
	public boolean offer(){
		boolean result=true;
		return result;
	}
}

class PropertyRowMapper implements RowMapper<PropertyBean>{

	@Override  
	public PropertyBean mapRow(ResultSet resultSet, int line) throws SQLException {   
		PropertyExtractor userExtractor = new PropertyExtractor();   
		return userExtractor.extractData(resultSet);   
	} 
}
