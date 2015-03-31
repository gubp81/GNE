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
public class OfferController {

	@Autowired
	PropertyDao propdao;

	@RequestMapping(value="/offer", method=RequestMethod.POST)
	public ModelAndView offer(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("amount") String amount)
	{
		boolean posted = false;
		String msg = "Error posting Offer.";
		posted = propdao.offer(name, phone, email, amount); 
		if (posted){
			
		/*	list=propdao.searchAny(
					propertyid,
					propType,
					size,
					price,
					region,
					sort,
					school,
					metro,
					hospital,
					shopping_mall
					);
			
			*/
			msg = "Offer successfully posted";
			return new ModelAndView("offer", "msg", msg);
		}
		return new ModelAndView("error", "msg", msg);

	}	

}
