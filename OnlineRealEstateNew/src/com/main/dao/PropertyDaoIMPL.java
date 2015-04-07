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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.main.util.*;

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
	public boolean makeanOffer(int propertyid,
			String name,
			String phone,
			String email,			
			String amount
			){
		boolean result = false;
		int buyer = 0 , property = 0;
		String sql, sqlUser;

		sqlUser="Select userid from \"User\" where email ='"+email+"'";
		System.out.println("SQL: "+sqlUser);
		
		try {
			buyer=getJdbcTemplate().queryForInt(sqlUser);			
		} catch (DataAccessException e){
			System.out.println("User not found");	
			sql="Insert into \"User\" ( name, phone, email, type) VALUES ('"+name+"','"+phone+"','"+email+"',1)";
			System.out.println("SQL: "+sql);
			try {
				buyer=getJdbcTemplate().queryForInt(sqlUser);
			} catch (DataAccessException e1) {
				e1.printStackTrace();
			}			
		}
		System.out.println("User found: "+ buyer);	
		try {
		sql="Select propertyid from \"Offer\" where propertyid = "+propertyid+"buyerid ="+buyer+"";
		System.out.println("SQL: "+sql);
		property = getJdbcTemplate().queryForInt(sql);
		if(property!=0){
			System.out.println("Buyer already posted an offer to this property: "+propertyid);
			return result;
		}		
		} catch (DataAccessException e){	
		}

		sql="Insert into \"Offer\" ( buyerid, propertyid, amount, date) VALUES ('"+buyer+"','"+propertyid+"','"+amount+"',now())";
		System.out.println("SQL: "+sql);
		int row=getJdbcTemplate().update(sql);
		if(row==1){
			sql="Update \"Property\" set offersmade = offersmade + 1 where propertyid = "+property;
			row=getJdbcTemplate().update(sql);
			if(row==1)
				System.out.println("Offer successfully posted");
	    	sendEmail(name, email);
				result=true;
		}
		return result;
	}
	private void sendEmail(String name, String email) {
		ApplicationContext context = 
		        new ClassPathXmlApplicationContext("/WEB-INF/Spring-Mail.xml");
   
		MailMail mm = (MailMail) context.getBean("MailMail");
		   mm.sendMail(email,
			   email,
			   "Offer Confirmation", 
			   "Hello "+name+". You are closer to your new home. Your offer is confirmed.");
	}

}

class PropertyRowMapper implements RowMapper<PropertyBean>{

	@Override  
	public PropertyBean mapRow(ResultSet resultSet, int line) throws SQLException {   
		PropertyExtractor userExtractor = new PropertyExtractor();   
		return userExtractor.extractData(resultSet);   
	} 
}
