package com.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.MemberVo;

@WebServlet("/search")
public class MemberController extends HttpServlet {

	// 화면 보여주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("searchType");
		String value = req.getParameter("searchValue");
		String cureentPage = req.getParameter("currentPage");
		
		List<MemberVo> memberList = new MemberService().search(type, value);
		
		req.setAttribute("memberList", memberList);
		req.getRequestDispatcher("/WEB-INF/views/member/searchAllUser.jsp").forward(req, resp);
	}
}
