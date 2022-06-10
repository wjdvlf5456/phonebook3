package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/pbc")
public class PhoneController {

	// 필드
	// 생성자
	// 메소드 - gs
	// 메소드-일반
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneBookController > writeForm");

		return "/WEB-INF/views/writeForm.jsp";
	}

	@RequestMapping("/pbc")
	public String phonebook() {
		System.out.println("/phonebook3/pbc");
		return "/WEB-INF/views/index.jsp";
	}

	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test() {
		System.out.println("PhoneController, test");
		// 다오

		return "/WEB-INF/views/test.jsp";
	}

}
