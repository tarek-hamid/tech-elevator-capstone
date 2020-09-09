package com.techelevator.controller;

import com.techelevator.dao.BeerDAO;
import com.techelevator.dao.BreweryDAO;
import com.techelevator.dao.RatingDAO;
import com.techelevator.entity.Beer;
import com.techelevator.entity.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path="/brewer")
public class BrewerController {

    private BreweryDAO breweryDAO;
    private BeerDAO beerDAO;
    private RatingDAO ratingDAO;

    @Autowired
    public BrewerController(BreweryDAO breweryDAO, BeerDAO beerDAO, RatingDAO ratingDAO) {
        this.breweryDAO = breweryDAO;
        this.beerDAO = beerDAO;
        this.ratingDAO = ratingDAO;
    }

    @RequestMapping(path="/addBeer", method= RequestMethod.GET)
    public String displayAddBeerForm(HttpServletRequest request) {
        int breweryId = Integer.parseInt(request.getParameter("breweryId"));
        request.setAttribute("breweryId", breweryId);
        return "user/addBeer";
    }

    @RequestMapping(path="/addBeer", method=RequestMethod.POST)
    public String addBeer(@Valid @ModelAttribute Beer beer, BindingResult result, RedirectAttributes flash, HttpServletRequest request) {
        int breweryId = Integer.parseInt(request.getParameter("breweryId"));
        if(result.hasErrors()) {
            flash.addFlashAttribute("beer", beer);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "beer", result);
            return "redirect:/brewer/addBeer";
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

    @RequestMapping(path="/addBrewery", method=RequestMethod.GET)
    public String displayAddBreweryForm() {
        return "user/addBrewery";
    }

    @RequestMapping(path="/addBrewery", method=RequestMethod.POST)
    public String createBrewery(@Valid @ModelAttribute Brewery brewery, BindingResult result, RedirectAttributes flash) {
        if(result.hasErrors()) {
            flash.addFlashAttribute("brewery", brewery);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "brewery", result);
            return "redirect:/brewer/addBrewery";
        }
        try {
            breweryDAO.saveBrewery(brewery);
        } catch (Exception exc){
            System.out.println(exc.getMessage());
            // good place to log
            return "redirect:/error";
        }
        return "redirect:/confirmation";
    }

    @RequestMapping(path="/updateBrewery", method=RequestMethod.GET)
    public String displayUpdateBreweryForm(HttpServletRequest request, ModelMap modelHolder) {
        int breweryId = Integer.parseInt(request.getParameter("breweryId"));
        modelHolder.put("breweryId", breweryId);
        return "user/updateBrewery";
    }

    @RequestMapping(path="/updateBrewery", method=RequestMethod.POST)
    public String updateBrewery(@Valid @ModelAttribute Brewery brewery, BindingResult result, RedirectAttributes flash, HttpServletRequest request) {
        if(result.hasErrors()) {
            flash.addFlashAttribute("brewery", brewery);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "brewery", result);
            return "redirect:/brewer/updateBrewery";
        }
        try {
            breweryDAO.updateBrewery(brewery);
        } catch (Exception exc){
            System.out.println(exc.getMessage());
            // good place to log
            return "redirect:/error";
        }
        return "redirect:/confirmation";
    }

}
