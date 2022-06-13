package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/pbc")
public class PhoneController {

	// 필드
	// 생성자
	// 메소드 - gs
	// 메소드-일반
	
	// ===================================== 목록 =====================================
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String List(Model model) {
		System.out.println("PhoneController > list()");
		
		//Dao를 통해서 personList(주소)를 가져온다.
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.personSelect();
		
		// ds 데이터보내기 --> request Attribute 에 넣는다.
		model.addAttribute("personList" , personList);
		
		//list.jsp로 포워딩 
		return "/WEB-INF/views/list.jsp";
	}
	// ===================================== 등록 =====================================
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhoneBookController > write");

		// 파라미터 꺼내기
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);

		// vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		// dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personinsert(personVo);
		System.out.println(count);
		
		//리다이렉트
		//리스트로 리다이렉트 시킬 예정
		
		return "redirect:/pbc/list";
		
		//return "/WEB-INF/views/writeForm.jsp";
	}

	// 전화번호 등록폼

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

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
