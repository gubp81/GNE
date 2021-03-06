/**
 * Class MainController that controls all requests from pages
 */
package com.main.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.main.beans.OfferBean;
import com.main.beans.PropertyBean;
import com.main.dao.PropertyDao;
import com.main.util.MailMail;

@Controller
public class MainController {

	List<PropertyBean> list=null;
	List<OfferBean> offers=null;
	@Autowired
	PropertyDao propdao;

	ApplicationContext context = 
			new ClassPathXmlApplicationContext("classpath*:Spring-Mail.xml");

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
		String msg = "";
		System.out.println("Search Values:"+
				proptype+"\r\n"+
				size+"\r\n"+
				price+"\r\n"+
				region+"\r\n"+
				sort+"\r\n"+
				garage+"\r\n"+
				pool+"\r\n"+
				ac+"\r\n"+
				school+"\r\n"+
				metro+"\r\n"+
				hospital+"\r\n"+
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
		if(list.size()==0){ 
			msg="No Records Found with the search criteria. Please refine your search";
			return new ModelAndView("message","msg", msg);}
		return new ModelAndView("searchResults", "list", list);
	}
	@RequestMapping(value="/details", method=RequestMethod.GET)
	public ModelAndView MakeanOffer(
			@RequestParam("propertyid") int propertyid ){
		PropertyBean property = propdao.getDetails(propertyid);
		return new ModelAndView("details","property",property);		
	}

	@RequestMapping(value="/makeanoffer", method=RequestMethod.POST)
	public ModelAndView makeanOffer(
			@RequestParam("propertyid") int propertyid,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("amount") String amount)
	{
		String msg;
		System.out.println("Came to controller makeanOffer");
		boolean posted=false;
		posted = propdao.makeanOffer(propertyid, name, phone, email, amount); 
		if (posted){

			msg="Hello "+name+"!"
					+ "\r\nYou are closer to your new home."
					+ "\r\nYour offer of $"+amount+" was sent to Seller's email."
					+ "\r\nYou will receive a email if offer is accepted or rejected."
					+ "\r\n(this offer expires within 3 days)\r\n"
					+ "\r\nThank you,\r\nGNE Properties";
			MailMail mm = (MailMail) context.getBean("MailMail");
			mm.sendMail(email,
					email,
					"GNE Properties: Offer Confirmation of $"+amount, 
					msg);		
			String sellersEmail = propdao.getSellersEmail(propertyid);

			MailMail mm2 = (MailMail) context.getBean("MailMail");
			mm2.sendMail(email,
					sellersEmail,
					"GNE Properties: Offer Received of $"+amount, 
					"Good news!"
					+ "\r\nYou just got an offer posted for your property."
					+ "\r\n"+name+" made an offer of $"+amount
					+ "\r\n.Please follow the link to accept or reject: http://localhost:8080/gne/offers?propertyid="+propertyid+"\r\n"
					+ "\r\nThank you,\r\n GNE Properties.");
		}
		else
			msg="Error posting Offer. Please contact the Administrator.";

		return new ModelAndView("message","msg",msg);	

	}

	@RequestMapping(value="/offers", method=RequestMethod.GET)
	public ModelAndView listOffers( 
			@RequestParam("propertyid") int propertyid )
	{
		System.out.println("Controller: Offer Decision");
		offers = propdao.listOffers(propertyid);

		if (offers!=null){
			for(int i=0;i<offers.size();i++){		
				System.out.println("Property:"+offers.get(i).getpropertyid()+"-Buyer:"+offers.get(i).getbuyerid()+"-Offer:"+offers.get(i).getamount());
			}
			return new ModelAndView("offers","offers",offers);
		}
		String msg = "Property was sold or not found.";
		return new ModelAndView("message","msg",msg);
	}

	@RequestMapping(value="/decision", method=RequestMethod.POST)
	public ModelAndView offerDecision( 
			@RequestParam("propertyid") int propertyid,
			@RequestParam("buyerid") int buyerid, 
			@RequestParam("action") String decision)
	{
		System.out.println("Came to controller offerDecision");

		OfferBean offer = null;
		String msg = "";
		String sellersEmail;

		System.out.println("Controller: Offer Decision"+"Property:"+propertyid+"-Buyer:"+buyerid+"Decision:"+decision);
		offer = propdao.offerDecision(propertyid, buyerid,decision);
		if(offer==null){
			msg="This offer is not valid anymore. If you have any question contact our Administration.";
			System.out.println(msg);
			return new ModelAndView("message","msg",msg);	
		}

		sellersEmail = propdao.getSellersEmail(propertyid);
		if(offer.isaccepted){
			//Buyer's Email
			msg="Congratulations for you new home!"
					+ "\r\nYour offer was accepted. An email with your contact information was sent to Seller("+sellersEmail+")."
					+ "\r\nYou will be contacted soon.\r\n"
					+ "\r\nThank you,\r\nGNE Properties.";
			System.out.println(msg);

			MailMail mm = (MailMail) context.getBean("MailMail");
			mm.sendMail(offer.email,
					offer.email,
					"GNE Properties: Offer of $"+offer.amount+" accepted!", 
					msg);
			//Seller's Email
			msg="Congratulations! \r\n You sold your property.\r\n"
					+ "\r\nAn email confirming your decision was sent to the buyer.\r\n"
					+ "\r\nHere's buyer information for contact:"
					+ "\r\nName:"+offer.name+"."
					+ "\r\nEmail:"+offer.email+"."
					+ "\r\nPhone Number:"+offer.phone+"\r\n"
					+ "\r\nThank you,\r\nGNE Properties.";
			System.out.println(msg);
			MailMail mm2 = (MailMail) context.getBean("MailMail");
			mm2.sendMail(sellersEmail,
					sellersEmail,
					"GNE Properties: Offer of $"+offer.amount+" accepted!", 
					msg);}
		else if (offer.isrejected){
			//Buyer's Email
			msg="Unfortunately, Your offer posted on "+offer.date+" of "+offer.amount+" was refused by the Seller. "
					+ "\r\nYou can post another offer anytime at our system.\r\n"
					+ "\r\nThank you,\r\nGNE Properties.";
			System.out.println(msg);
			MailMail mm = (MailMail) context.getBean("MailMail");
			mm.sendMail(offer.email,
					offer.email,
					"GNE Properties: Offer refused!", 
					msg);
			//Seller's Email
			msg="An offer posted on "+offer.date+" of "+offer.amount+" was refused by you. "
				+ "\r\nAn email confirming your decision will be send to the buyer.\r\n "
				+ "\r\nThank you,\r\nGNE Properties.";
			System.out.println(msg);
			MailMail mm2 = (MailMail) context.getBean("MailMail");
			mm2.sendMail(sellersEmail,
					sellersEmail,
					"GNE Properties: Offer of "+offer.amount+" Refused", 
					msg);
		}
		return new ModelAndView("message","msg",msg);	
	}


	@RequestMapping(value="/sell", method=RequestMethod.GET)
	public ModelAndView sell(
			@RequestParam(required=false, value="deleteFlag", defaultValue = "false") boolean deleteFlag,
			@RequestParam(required=false, value="propertyid", defaultValue = "0") int propertyid)
	{
		String msg = "";
		System.out.println("Came to controller sell GET.Property"+propertyid+" Delflag:"+deleteFlag);
		if(deleteFlag){
			propdao.remove(propertyid);
			msg= "Property successfully deleted from our system";
			return new ModelAndView("message","msg",msg);
		}		
		return new ModelAndView("sell","",false);
	}

	@RequestMapping(value="/sell", method=RequestMethod.POST)
	public ModelAndView sell(
			@RequestParam("name") String name,
			@RequestParam("phone") String phone,			
			@RequestParam("email") String email,
			@RequestParam("price") int price,	
			@RequestParam("address") String address,
			@RequestParam("region") String region,			
			@RequestParam("proptype") String proptype,
			@RequestParam("size") String size,
			@RequestParam("year") int year,
			@RequestParam(required=false, value="garage") boolean garage,
			@RequestParam(required=false, value="pool") boolean pool,
			@RequestParam(required=false, value="ac") boolean ac,			
			@RequestParam(required=false, value="school") boolean school,
			@RequestParam(required=false, value="metro") boolean metro,
			@RequestParam(required=false, value="hospital") boolean hospital,
			@RequestParam(required=false, value="shopping_mall") boolean shopping_mall,
			@RequestParam(required=false, value="description") String description,
			@RequestParam(required=false, value="image") String image)
	{
		String msg = "";
		System.out.println("Came to controller Sell");
		System.out.println("Parameters: "+name+ phone+ email+ 
				proptype+
				size+
				price+
				address+
				region+
				year+
				description+
				garage+
				pool+
				ac+
				school+
				metro+
				hospital+
				shopping_mall+
				image);
		int propertyid = 0;

		propertyid = propdao.post(name, phone, email, 
				proptype,
				size,
				price,
				address,
				region,
				year,
				description,
				garage,
				pool,
				ac,
				school,
				metro,
				hospital,
				shopping_mall); 
		if (propertyid!=0){

			msg="Hello "+name+". \r\nYour Property at "+address+" was successfully posted in our system."
					+ "\r\nYou can see details of your property here: http://localhost:8080/gne/details?propertyid="+propertyid
					+ "\r\nYou can delete this post from our system by clicking here: http://localhost:8080/gne/sell?propertyid="+propertyid+"&deleteFlag=true \r\n"
					+ "\r\nThank you,\r\nGNE Properties";

			MailMail mm = (MailMail) context.getBean("MailMail");
			mm.sendMail(email,
					email,
					"GNE Properties: Property post Confirmation", 
					msg);		
			PropertyBean property = propdao.getDetails(propertyid);
			property.setSoldValue("Sold");
			return new ModelAndView("details","property",property);		
		}
		else
			msg="Error posting Property. Please contact the Administrator.";

		return new ModelAndView("message","msg",msg);	

	}
}
