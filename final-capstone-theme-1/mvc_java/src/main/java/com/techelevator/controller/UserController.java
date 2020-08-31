package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.techelevator.security.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.entity.User;
import com.techelevator.dao.UserDAO;

@Controller
public class UserController {

	private UserDAO userDAO;

	@Autowired
	public UserController(UserDAO userDAO) {

		this.userDAO = userDAO;
	}

	@RequestMapping(path="/register", method=RequestMethod.GET)
	public String displayRegisterPage(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "user/register";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors() || (!user.getPassword().equals(user.getConfirmPassword()))) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/register";
		}
		try {
			userDAO.saveUser(user);
		} catch (Exception exc){
			// good place to log
			return "redirect:/error";
		}
		return "redirect:/login";
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "user/login";
	}

	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String autheticateUser(@RequestParam String userName,
						@RequestParam String password,
						@RequestParam(required=false) String destination,
						HttpSession session) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute(AuthorizationFilter.LOGGED_USER, userDAO.getUserByUserName(userName));

			if(destination != null && ! destination.isEmpty()) {
				return "redirect:" + destination;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		model.remove(AuthorizationFilter.LOGGED_USER);
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(path="/forgot-password", method=RequestMethod.GET)
	public String displayForgotPassword() {
		return "user/forgot-password";
	}


}
