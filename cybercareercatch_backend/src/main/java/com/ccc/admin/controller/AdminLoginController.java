package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;

public class AdminLoginController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Result result = new Result();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("adminId")) { 
					request.setAttribute("adminId", cookie.getValue());
				}
			}
		}
		result.setPath("/app/admin/admin-login.jsp");
		result.setRedirect(false);
		return result;
	}

}
