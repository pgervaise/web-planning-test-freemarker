package fr.penelope.planning.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Philippe Gervaise
 *
 */
@Controller
public class IndexController {

	public IndexController() {
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewIndex(HttpServletRequest request, HttpSession session, Model model) {
		return "index";
	}
}
