/**
 * 
 */
package com.main.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.main.beans.PropertyBean;
import com.main.dao.PropertyDao;

@Controller

public class MainController {
	@Autowired
	PropertyDao propdao;
	@RequestMapping(value="/test", method=RequestMethod.POST)
	   public ModelAndView PropertySearchSelect(@RequestParam("propType") String propType,
			   @RequestParam("size") String size,
			   @RequestParam("price") String price,
			   @RequestParam("region") String region,
			   @RequestParam("sort") String sort,
			   @RequestParam(required=false, value="school") boolean school,
			   @RequestParam(required=false, value="metro") boolean metro,
			   @RequestParam(required=false, value="hospital") boolean hospital,
			   @RequestParam(required=false, value="shopping_mall") boolean shopping_mall
			   ) {
		List<PropertyBean> list=null;
		
			System.out.println("Values:"+propType+ "size:"+size+"Sort by:"+sort);
			list=propdao.searchAny(propType,
					size,
					price,
					region,
					sort,
					school,
					metro,
					hospital,
					shopping_mall
					);
			if(list.size()==0){
				return new ModelAndView("error","list",list);
			}
		return new ModelAndView("body1", "list",list);
	}
	
	}

