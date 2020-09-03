package com.techelevator.controller;

import com.techelevator.dao.BreweryDAO;
import com.techelevator.entity.Brewery;
import com.techelevator.util.EmployeeDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path="/user")
public class ContentController {

	private BreweryDAO breweryDAO;

	@Autowired
	public ContentController(BreweryDAO breweryDAO){
		this.breweryDAO = breweryDAO;
	}

	@RequestMapping(path="/dashboard", method=RequestMethod.GET)
	public String displayDashboard(ModelMap modelHolder) {
		List<Brewery> breweries = breweryDAO.getAllBreweries();
		modelHolder.put("breweries", breweries);
		return "user/dashboard";
	}

	@RequestMapping(path="/search", method=RequestMethod.GET)
	public String displaySearchResults() {
		return "examples/searchResults";
	}

	@RequestMapping(path="/blank", method=RequestMethod.GET)
	public String displayBlankPage() {

		return "common/blank";
	}

	@RequestMapping(path="/404", method=RequestMethod.GET)
	public String displayBadPage() {

		return "common/404";
	}

	@RequestMapping(path="/basic/table", method=RequestMethod.GET)
	public String displayBasicTable() {

		return "examples/basicTableExample";
	}

	@RequestMapping(path="/rest/table", method=RequestMethod.GET)
	public String displayRestTable() {

		return "examples/restTableExample";
	}

	@RequestMapping(path="/buttons", method=RequestMethod.GET)
	public String displayButtons() {

		return "examples/buttons";
	}

	@RequestMapping(path="/cards", method=RequestMethod.GET)
	public String displayCards() {

		return "examples/cards";
	}

	@RequestMapping(path="/charts", method=RequestMethod.GET)
	public String displayCharts() {

		return "examples/charts";
	}

	@RequestMapping(path="/accordion", method=RequestMethod.GET)
	public String displayAccordion(ModelMap modelMap) {
		modelMap.put("employees", EmployeeDataTable.getInstance().getData());
		return "examples/accordionExample";
	}
	
}
