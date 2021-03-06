package com.hello.aws;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hello.aws.data.DynamoDBDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		LOG.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String name = "supercal";
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("name", name);
		
		return "home";
	}

	@RequestMapping(value = "/formHandler", method = RequestMethod.POST)
	public String formHandler(Model model, @RequestBody String body) {
		LOG.debug(body);
		
		DynamoDBDao dao = new DynamoDBDao();
		
		dao.insertDummyDataToTable();
		
		model.addAttribute("submitData", body);
		return "home";
		
	}
	
}
