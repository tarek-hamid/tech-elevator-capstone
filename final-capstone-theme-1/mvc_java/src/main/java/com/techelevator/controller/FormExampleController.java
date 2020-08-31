package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.entity.User;
import com.techelevator.example.FormExample;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FormExampleController {

	@RequestMapping(path="/formExample", method=RequestMethod.GET)
	public String showFormExample(ModelMap modelHolder) {
		if (!modelHolder.containsAttribute("formExample")) {
			modelHolder.put("formExample", new FormExample());
		}

		return "examples/formExample";
	}

	@RequestMapping(path = "/formExample", method = RequestMethod.POST)
	public String processFormExample(
			@Valid @ModelAttribute FormExample formExample,
			BindingResult result,
			RedirectAttributes flash) {

		flash.addFlashAttribute("formExample", formExample);

		if (result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "formExample", result);
			return "redirect:/formExample";
		}
		// This is where the Business or DAO object used to insert or update form data

		return "redirect:/formExampleUpdated";
	}

	@RequestMapping(path = "/formExampleUpdated", method = RequestMethod.GET)
	public String showExampleFormUpdatedPage() {

		return "examples/formExampleUpdated";
	}
}
