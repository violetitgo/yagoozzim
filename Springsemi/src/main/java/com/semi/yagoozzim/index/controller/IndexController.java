package com.semi.yagoozzim.index.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.semi.yagoozzim.index.model.service.IndexService;



@Controller
public class IndexController {
	@Autowired
	IndexService is;
	
	
	@RequestMapping("index/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
		String sysdate = sdf.format(cal.getTime());
		cal.add(cal.DATE, 1);
		String tomorrow = sdf.format(cal.getTime());
		cal.add(cal.DATE, 1);
		String aftertomorrow = sdf.format(cal.getTime());

		System.out.println(sysdate);
		System.out.println(tomorrow);
		System.out.println(aftertomorrow);

		Map<String,Object> res = is.selectData(sysdate, tomorrow, aftertomorrow);

		System.out.println(res);
		// 여기 직전에 데이터를 담아서 보내자.
		mav.addObject("dataList", res);

		mav.setViewName("index/index");

		return mav;
	}
	
	@RequestMapping("index/about")
	public ModelAndView aboutUs() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index/about");
		return mav;
	}
	

	
	

}
