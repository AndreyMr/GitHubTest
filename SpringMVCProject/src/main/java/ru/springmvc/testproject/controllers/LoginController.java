package ru.springmvc.testproject.controllers;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import ru.springmvc.testproject.objects.User;

@Controller
@SessionAttributes("user")
public class LoginController {
	Logger loger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private MessageSource messageSource;

	@ModelAttribute
	public User createNewUser() {
		return new User("superNewUser");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(@ModelAttribute User user, HttpSession session, Locale locale) {
		loger.info(locale.getDisplayLanguage());
		loger.info(messageSource.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(Locale locale, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, RedirectAttributes reAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("locale", messageSource.getMessage("locale", new
		// String[] { locale.getDisplayName(locale) }, locale));

		if (!bindingResult.hasErrors()) {
			RedirectView redirectView = new RedirectView("mainpage");
			redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			modelAndView.setView(redirectView);

			reAttributes.addFlashAttribute("locale", messageSource.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
		} else {
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String goMainPage(HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			loger.info("Redirect!");
		} else {
			loger.info("Update!");
		}
		return "loginresult";
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public String failed(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("message", "Login failed!");
		return "login-failed";
	}

	@RequestMapping(value = "/get-json-user/{name}{admin}", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
		User user = new User();
		user.setName(name);
		user.setAdmin(admin);
		return user;
	}

	@RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> setJsonUser(@RequestBody User user) {
		loger.info(user.getName());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
