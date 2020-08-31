package com.techelevator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IconsController {

	public IconsController(){
	}

	@RequestMapping(path="/favicon.ico", method=RequestMethod.GET)
	public @ResponseBody String favIcon() {
		return "/favicon.png";
	}

	@RequestMapping(path="/vendor/fontawesome-free/webfonts/fa-solid-900.woff2", method=RequestMethod.GET)
	public String vendorFontsSolid900WoffTwo() {

		return "/vendor/fontawesome-free/webfonts/fa-solid-900.woff2";
	}

	@RequestMapping(path="/vendor/fontawesome-free/webfonts/fa-solid-900.woff", method=RequestMethod.GET)
	public String vendorFontsSolid900Woff() {

		return "/vendor/fontawesome-free/webfonts/fa-solid-900.woff";
	}

	@RequestMapping(path="/vendor/fontawesome-free/webfonts/fa-solid-900.ttf", method=RequestMethod.GET)
	public String vendorFontsSolid900ttf() {

		return "/vendor/fontawesome-free/webfonts/fa-solid-900.ttf";
	}
	
}
