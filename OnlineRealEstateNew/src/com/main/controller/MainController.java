/**
 * Class MainController that controls all requests from pages
 */
package com.main.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.main.beans.OfferBean;
import com.main.beans.PropertyBean;
import com.main.dao.PropertyDao;

@Controller
public class MainController {

	List<PropertyBean> list=null;
	@Autowired
	PropertyDao propdao;
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView PropertySearchSelect(
			@RequestParam("proptype") String proptype,
			@RequestParam("size") String size,
			@RequestParam("price") String price,
			@RequestParam("region") String region,
			@RequestParam("sort") String sort,		
			@RequestParam(required=false, value="garage") boolean garage,
			@RequestParam(required=false, value="pool") boolean pool,
			@RequestParam(required=false, value="ac") boolean ac,			
			@RequestParam(required=false, value="school") boolean school,
			@RequestParam(required=false, value="metro") boolean metro,
			@RequestParam(required=false, value="hospital") boolean hospital,
			@RequestParam(required=false, value="shopping_mall") boolean shopping_mall
			) {
		List<PropertyBean> list=null;

		System.out.println("Search Values:"+
				proptype+
				size+
				price+
				region+
				sort+
				garage+
				pool+
				ac+
				school+
				metro+
				hospital+
				shopping_mall);
		list=propdao.searchAny(
				proptype,
				size,
				price,
				region,
				sort,
				garage,
				pool,
				ac,
				school,
				metro,
				hospital,
				shopping_mall
				);
		if(list.size()==0)
			return new ModelAndView("error","list", list);
		return new ModelAndView("searchResults", "list", list);
	}
	@RequestMapping(value="/offer", method=RequestMethod.GET)
	public ModelAndView MakeanOffer(
			@RequestParam("propertyid") int propertyid			){
		PropertyBean property = propdao.getDetails(propertyid);
		return new ModelAndView("offer","property",property);		
	}

	@RequestMapping(value="/makeanoffer", method=RequestMethod.POST)
	public ModelAndView makeanOffer(
			@RequestParam("propertyid") int propertyid,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("amount") String amount)
	{
		OfferBean bean=new OfferBean();
		bean.setPropertyId(propertyid);
		bean.setName(name);
		bean.setAmount(amount);
		bean.setPhone(phone);
		bean.setEmail(email);
		System.out.println("Came to controller makeanOffer");
		boolean posted=false;
		posted = propdao.makeanOffer(propertyid, name, phone, email, amount); 
		if (posted){

			return new ModelAndView("posted","list",bean);
		}

		return new ModelAndView("failure","result",posted);			
	}

}
