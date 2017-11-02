package com.demo.springpoc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springpoc.controller.LoginService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info("enter displayLogin: request = [" + request + "], response = [" + response + "]");

		ModelAndView model = new ModelAndView("login");

		LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);

		logger.info("exit displayLogin: res = [" + model + "]");

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute LoginBean loginBean) {
		ModelAndView model = null;

		logger.info("enter executeLogin: request = [" + request + "], response = [" + response + "], loginBean = [" + loginBean + "]");

		if (loginService.doLogin(loginBean)) {
			model = new ModelAndView("success");
		} else {
			model = new ModelAndView("fail");
		}
		
		logger.info("exit displayLogin: res = [" + model + "]");

		return model;
	}
}
