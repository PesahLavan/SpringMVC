package com.mince.training.controllers;

import com.mince.training.objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

    @Autowired
    private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(@ModelAttribute User user, HttpSession session, Locale locale) {
        System.out.println(locale.getDisplayLanguage());
        System.out.println(messageSource.getMessage("locale",new String[] {locale.getDisplayName(locale)}, locale));
        user.setName("usernamevalue");
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Locale locale) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("locale", messageSource.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
        } else {
            modelAndView.setViewName("main");
        }

        return modelAndView;
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView failed() {
		return new ModelAndView("login-failed", "message", "Login failed!");
	}

    @RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
        User user = new User();
        user.setName(name);
        user.setAdmin(admin);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        logger.info(user.getName());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
