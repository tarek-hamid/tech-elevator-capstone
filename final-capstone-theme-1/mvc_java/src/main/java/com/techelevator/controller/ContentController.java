package com.techelevator.controller;

import com.techelevator.dao.BeerDAO;
import com.techelevator.dao.BreweryDAO;
import com.techelevator.dao.RatingDAO;
import com.techelevator.entity.Beer;
import com.techelevator.entity.Brewery;
import com.techelevator.entity.Rating;
import com.techelevator.util.EmployeeDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/user")
public class ContentController {

	private BreweryDAO breweryDAO;
	private BeerDAO beerDAO;
	private RatingDAO ratingDAO;

	@Autowired
	public ContentController(BreweryDAO breweryDAO, BeerDAO beerDAO, RatingDAO ratingDAO){
		this.beerDAO = beerDAO;
		this.breweryDAO = breweryDAO;
		this.ratingDAO = ratingDAO;
	}

	@RequestMapping(path="/dashboard", method=RequestMethod.GET)
	public String displayDashboard(ModelMap modelHolder) {
		List<Brewery> breweries = breweryDAO.getAllBreweries();
		modelHolder.put("breweries", breweries);
		return "user/dashboard";
	}

	@RequestMapping("/dashboard/breweryDetails")
	public String displayBreweryDetails(HttpServletRequest request, ModelMap modelMap) {
		int breweryId = Integer.parseInt(request.getParameter("id"));
		Brewery brewery = breweryDAO.getBreweryById(breweryId);
		List<Beer> beerList = new ArrayList<>();
		beerList = beerDAO.getBeerByBreweryId(breweryId);

		request.setAttribute("brewery", brewery);
		modelMap.put("beers", beerList);

		return "user/breweryDetails";
	}

	@RequestMapping("/dashboard/beerDetails")
	public String displayBeerDetails(HttpServletRequest request) {
		int beerId = Integer.parseInt(request.getParameter("id"));
		Beer beer = beerDAO.getBeerByID(beerId);

		request.setAttribute("beer", beer);

		return "user/beerDetails";
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

	@RequestMapping(path="/addBeer", method=RequestMethod.GET)
	public String displayAddBeerForm() {
		return "user/addBeer";
	}

	@RequestMapping(path="/addBeer", method=RequestMethod.POST)
	public String addBeer(@Valid @ModelAttribute Beer beer, BindingResult result, RedirectAttributes flash, HttpServletRequest request) {
		int breweryId = Integer.parseInt(request.getParameter("id"));
		if(result.hasErrors()) {
			flash.addFlashAttribute("beer", beer);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "beer", result);
			return "redirect:/addBeer";
		}
		try {
			beerDAO.addBeer(beer, (long)breweryId);
		} catch (Exception exc){
			System.out.println(exc.getMessage());
			// good place to log
			return "redirect:/error";
		}
		return "redirect:/confirmation";
	}


	@RequestMapping(path="/deleteBeer", method=RequestMethod.GET)
	public String deleteBeer(HttpServletRequest request) {
		int beerId = Integer.parseInt(request.getParameter("id"));
		try {
			beerDAO.deleteBeer(beerId);
		} catch (Exception exc){
			System.out.println(exc.getMessage());
			// good place to log
			return "redirect:/error";
		}
		return "redirect:/user/dashboard";
	}

	@RequestMapping(path="/reviewBeer", method=RequestMethod.GET)
	public String displayReviewBeerForm(ModelMap modelHolder, HttpServletRequest request) {
		if (!modelHolder.containsAttribute("rating")){
			modelHolder.put("rating", new Rating(Integer.parseInt(request.getParameter("id"))));
		}
		return "user/reviewBeer";
	}

	@RequestMapping(path="/reviewBeer", method=RequestMethod.POST)
	public String reviewBeer(@Valid @ModelAttribute Rating rating, BindingResult result, RedirectAttributes flash,
							 ModelMap modelHolder) {
		rating = (Rating) modelHolder.get("rating");
		if(result.hasErrors()) {
			flash.addFlashAttribute("rating", rating);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "rating", result);
			return "redirect:/reviewBeer";
		}
		try {
			ratingDAO.addRating(rating, rating.getBeerId());
		} catch (Exception exc){
			System.out.println(exc.getMessage());
			// good place to log
			return "redirect:/error";
		}
		return "redirect:/confirmation";
	}
	
}
