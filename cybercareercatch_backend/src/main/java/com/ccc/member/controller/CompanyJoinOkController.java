package com.ccc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dao.CompanyDAO;
import com.ccc.company.dto.CompanyJoinDTO;
import com.ccc.member.dao.MemberDAO;

public class CompanyJoinOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Result result = new Result();
		
		CompanyJoinDTO companyJoinDTO = new CompanyJoinDTO();
		CompanyDAO companyDAO = new CompanyDAO();
		MemberDAO memberDAO = new MemberDAO();
		
		companyJoinDTO.setCompanyName(request.getParameter("companyName"));
		companyJoinDTO.setCompanyBrn(request.getParameter("companyBrn"));
		companyJoinDTO.setCompanyAddress(request.getParameter("companyAddress"));
		companyJoinDTO.setUserName(request.getParameter("userName"));
		companyJoinDTO.setUserPhone(request.getParameter("userPhone"));
		companyJoinDTO.setUserId(request.getParameter("userId"));
		companyJoinDTO.setUserPw(request.getParameter("userPw"));
		
		companyDAO.join(companyJoinDTO);
		memberDAO.joinCompanyMember(companyJoinDTO);
		
		result.setPath(request.getContextPath() + "/member/login.mefc");
		result.setRedirect(true);
		
		return result;
	}

}
