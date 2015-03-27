/**
 * 
 */
package com.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.main.beans.PropertyBean;
import com.main.util.PropertyExtractor;

/**
 * @author test
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
	@Override
	public List<PropertyBean> searchAny(String propType,
			String size,
			String price,
			String region,
			String sort,
			boolean school,
			boolean metro,
			boolean hospital,
			boolean shopping_mall
			){
		String sql="select * from PropertyData ";
		
		if(!propType.equals("Click to Select")){
            sql+=" WHERE propType='"+propType+"'";
        }
//        if(sql.indexOf("WHERE")==-1){
//            if(!rent.equals("Click to Select"))
//            	sql+=" WHERE rent='"+rent+"'";
//        }
//        else{
//            if(!rent.equals("Click to Select"))
//            	sql += " and rent='"+rent+"'";
//        }
        
        if(sql.indexOf("WHERE")==-1){
            if(!size.equals("Click to Select"))
            	sql+=" WHERE size='"+size+"'";
        }
        else{
            if(!size.equals("Click to Select"))
            	sql += " and size='"+size+"'";
        }
        
        if(sql.indexOf("WHERE")==-1){
            if(!price.equals("Click to Select"))
            	sql+=" WHERE price='"+price+"'";
        }
        else{
            if(!price.equals("Click to Select"))
            	sql += " and price='"+price+"'";
        }
        
        if(sql.indexOf("WHERE")==-1){
            if(!region.equals("Click to Select"))
            	sql+=" WHERE region='"+region+"'";
        }
        else{
            if(!region.equals("Click to Select"))
            	sql += " and region='"+region+"'";
        }
        
        if(sql.indexOf("WHERE")==-1){
            if(school)
            	sql+=" WHERE school='"+school+"'";
        }
        else{
        	if(school)
            	sql+=" and school='"+school+"'";
        }
        if(sql.indexOf("WHERE")==-1){
            if(metro)
            	sql+=" WHERE metro='"+metro+"'";
        }
        else{
        	if(metro)
            	sql+=" and metro='"+metro+"'";
        }
        if(sql.indexOf("WHERE")==-1){
            if(hospital)
            	sql+=" WHERE hospital='"+hospital+"'";
        }
        else{
        	if(hospital)
            	sql+=" and hospital='"+hospital+"'";
        }
        if(sql.indexOf("WHERE")==-1){
            if(shopping_mall)
            	sql+=" WHERE shopping_mall='"+shopping_mall+"'";
        }
        else{
        	if(shopping_mall)
            	sql+=" and shopping_mall='"+shopping_mall+"'";
        }
        if(sort.equals("low to high")){
        	sql+=" order by price asc";
        }
        else if(sort.equals("high to low")){
        	sql+=" order by price desc";
        }
		List<PropertyBean> list = getJdbcTemplate().query(sql, new PropertyRowMapper());
		return list;
	}
}
class PropertyRowMapper implements RowMapper<PropertyBean>{

	@Override  
	 public PropertyBean mapRow(ResultSet resultSet, int line) throws SQLException {   
	  PropertyExtractor userExtractor = new PropertyExtractor();   
	  return userExtractor.extractData(resultSet);   
	 } 
	}
