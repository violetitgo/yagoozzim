package com.semi.yagoozzim.index.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		insertData();
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

		Map<String, Object> res = is.selectData(sysdate, tomorrow, aftertomorrow);

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

	public int insertData() {
		System.out.println("왔는가");
		List<Map<String, String>> datalist = webCrawling();

		System.out.println(datalist);
		System.out.println("서비스에게 넘기기 직전인 컨트롤러 입니다.");
		int result = is.insertData(datalist);
		System.out.println("서비스에게 값을 받아온 컨트롤러입니다.");
		if (result >= 1) {
			System.out.println("db입력 성공");
		} else {
			System.out.println("db입력 실패");
		}

		return result;

	}

	public List<Map<String, String>> webCrawling() {
		System.out.println("webcrawling 접근");
		System.setProperty("webdriver.chrome.driver","/chromedriver.exe");
		Document doc;
		System.out.println("Document 접근");	
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps");

		List<Map<String, String>> res = null;
		// Webdriver 객체생성
		ChromeDriver driver = new ChromeDriver(options);
		System.out.println("WebDriver 접근");

		try {
		
			
			
			
			WebDriverWait wait = new WebDriverWait(driver, 1000);

			driver.get("https://www.koreabaseball.com/Schedule/Schedule.aspx");
//	        System.out.println(driver.getPageSource());

			doc = Jsoup.parse(driver.getPageSource());

			/* System.out.println(doc); */
			// 무엇을 갖고 오는지 확인

			Elements day = doc.select("#tblSchedule tr");
			// 우리가 f12를 이용해서 원하는 데이터부분 우클릭하여 카피셀렉터
//	         System.out.println(tblSchedule);

			// Elements time = doc.select(".time");

			// Elements play = doc.select(".play");
			res = new ArrayList<>();
			String matchDay = "";
			for (Element data : day) {

				Map<String, String> mapData = new LinkedHashMap<>();

				if (!(data.select(".day").text().equals(""))) {
					matchDay = data.select(".day").text();
					/*
					 * System.out.println("담김"); System.out.println(matchDay);
					 */
				}

				mapData.put("day", matchDay);
				mapData.put("time", data.select(".time").text());
				mapData.put("play", data.select(".play").text());
				res.add(mapData);

			}

			/*
			 * for (Map<String, String> comeondata : res) { System.out.println(comeondata);
			 * // {day=, time=, play=}s }
			 */

		} finally {
			driver.quit();
		}
		return res;
	}

}
