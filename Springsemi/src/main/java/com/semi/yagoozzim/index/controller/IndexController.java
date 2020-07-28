package com.semi.yagoozzim.index.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	@Autowired
	
	
	@RequestMapping("index/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("index/index");
		return mav;
	}
	

}
