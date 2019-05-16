package ru.springmvc.testproject.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import ru.springmvc.testproject.exceptions.BadFileNameException;
import ru.springmvc.testproject.objects.UploadedFile;
import ru.springmvc.testproject.objects.User;
import ru.springmvc.testproject.validators.FileValidator;

@Controller

@SessionAttributes("user")
public class LoginController {
	Logger loger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private FileValidator fileValidator;

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

	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult bindingResult) throws IOException, BadFileNameException {

		ModelAndView modelAndView = new ModelAndView();
		MultipartFile file = uploadedFile.getFile();

		fileValidator.validate(file, bindingResult);

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("loginresult");
		} else {

			String fileName = file.getOriginalFilename();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			try {
				inputStream = file.getInputStream();

				File newFile = new File("F:/JavaProject/files/" + fileName);
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1)
					outputStream.write(bytes, 0, read);
				loger.info("Writing file: " + newFile.getAbsolutePath());
				// close stream
				inputStream.close();
				outputStream.close();

				RedirectView redirectView = new RedirectView("fileuploadresult");
				redirectView.setStatusCode(HttpStatus.FOUND);
				modelAndView.setView(redirectView);
				modelAndView.addObject("filename", fileName);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// throw new IOException("File not found");
			throw new BadFileNameException("Bad filename: " + fileName);
		}

		return modelAndView;
	}

	// перехват исключения с помощью аннотации @ExceptionHandler и вывод результатов
	// с помощью сервера приложений
	/*
	 * @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason =
	 * "IOException exception! Check argument")
	 * 
	 * @ExceptionHandler(IOException.class) public void handleIOException() {
	 * loger.error("IOExceptoin handler executed"); }
	 * 
	 * @ExceptionHandler(BadFileNameException.class) public ModelAndView
	 * hendleBadFileNameException(Exception ex) {
	 * loger.error("badFileNameException execute"); ModelAndView modelAndView = new
	 * ModelAndView("error"); modelAndView.addObject("error", ex.getMessage());
	 * return modelAndView; }
	 */

	@RequestMapping(value = "/fileuploadresult", method = RequestMethod.GET)
	public String fileUploadResult() {
		return "fileuploadresult";
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

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadPDF() {
		return new ModelAndView("pdfView");
	}

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("excelView");
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public String failed(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("message", "Login Failed!");
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
