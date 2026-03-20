package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.admin.dao.AdminDAO;
import com.ccc.admin.dto.AdminDTO;

public class AdminLoginOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AdminDTO adminDTO = new AdminDTO();
		AdminDAO adminDAO = new AdminDAO();
		int adminNumber = 0;
		Result result = new Result();
		
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		String remember = request.getParameter("remember");
		HttpSession session = request.getSession(); //+++ 세션저장
		String path = null;
		
		adminDTO.setAdminId(adminId);
		adminDTO.setAdminPw(adminPw);
		
		//쿼리문 실행 메소드 호출
		adminNumber = adminDAO.login(adminDTO);
		System.out.println(adminNumber); 
		
		if(adminNumber != -1) {
			path = request.getContextPath() + "/admin/main.adfc";
			session.setAttribute("adminNumber", adminNumber);
			System.out.println("세션값 : " + adminNumber);
			
			if(remember != null) {
				Cookie cookie = new Cookie("adminId", adminId);
				cookie.setMaxAge(60 * 60 * 24); //1일
				response.addCookie(cookie);
			}else {
				//체크 해제시 쿠키 삭제
				Cookie cookie = new Cookie("adminId", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}else {
			path = request.getContextPath() + "/admin/login.adfc?login=fail";
		}
		
		result.setRedirect(true); //세션에 저장된 값은 유지
		result.setPath(path);
		
		
		return result;
	}

}
