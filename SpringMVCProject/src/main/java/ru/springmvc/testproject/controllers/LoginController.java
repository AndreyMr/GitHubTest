package ru.springmvc.testproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.springmvc.testproject.objects.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(@ModelAttribute("user") User user) {
		return new ModelAndView("loginresult", "user", user);
	}

}
