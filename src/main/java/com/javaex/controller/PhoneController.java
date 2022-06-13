package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

import oracle.security.crypto.core.CipherException;

@Controller
//@RequestMapping(value = "/pbc")
public class PhoneController {

	// 필드
	// 생성자
	// 메소드 - gs
	// 메소드-일반

	// ===================================== 목록
	// =====================================
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String List(Model model) {
		System.out.println("PhoneController > list()");

		// Dao를 통해서 personList(주소)를 가져온다.
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.personSelect();

		// ds 데이터보내기 --> request Attribute 에 넣는다.
		model.addAttribute("personList", personList);

		// list.jsp로 포워딩
		return "/WEB-INF/views/list.jsp";
	}

	// 전화번호 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		return "/WEB-INF/views/writeForm.jsp";
	}

	// ===================================== 등록
	// =====================================
	// public String write(@RequestParam("name") String name, @RequestParam("hp")
	// String hp,@RequestParam("company") String company) {
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
//		public String write(@ModelAttribute PersonVo personVo, @RequestParam("age") int age) {
		System.out.println("PhoneBookController > write");

		// 파라미터 꺼내기
		/*
		 * System.out.println(name); System.out.println(hp);
		 * System.out.println(company);
		 */
		// vo로 묶기
		// PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		// dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personinsert(personVo);
		// System.out.println(age);
		System.out.println(count);

		// 리다이렉트
		// 리스트로 리다이렉트 시킬 예정

		return "redirect:/list";

		// return "/WEB-INF/views/writeForm.jsp";
	}

	// ===================================== 삭제
	// =====================================
	@RequestMapping(value = "/delete/{no},{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int no, @PathVariable("id") String id) {
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.persondelete(no);
		System.out.println(count);
		System.out.println(id);

		return "redirect:/list";
	}

	// ===================================== 수정폼
	// =====================================
	@RequestMapping(value = "/modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@PathVariable("no") int no, Model model) {

		PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = phoneDao.getPerson(no);
		model.addAttribute("personVo", personVo);
		return "/WEB-INF/views/modifyForm.jsp";
	}

	// ===================================== 수정
	// =====================================
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {
		
		System.out.println(personVo);

		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personUpdate(personVo);
		System.out.println(count);

		return "redirect:/list";
	}

}
