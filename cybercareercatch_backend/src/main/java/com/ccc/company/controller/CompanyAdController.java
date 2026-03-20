package com.ccc.company.controller; // 회사 컨트롤러 패키지이다.

import java.io.IOException; // 입출력 예외 처리를 위해 가져온다.
import java.util.List; // 여러 개의 기업 카드를 리스트로 받기 위해 가져온다.

import javax.servlet.ServletException; // 서블릿 예외 처리를 위해 가져온다.
import javax.servlet.http.HttpServletRequest; // 요청 객체를 사용하기 위해 가져온다.
import javax.servlet.http.HttpServletResponse; // 응답 객체를 사용하기 위해 가져온다.

import com.ccc.common.Execute; // Execute 인터페이스를 구현하기 위해 가져온다.
import com.ccc.common.Result; // 이동 경로와 이동 방식을 담는 Result 클래스를 가져온다.
import com.ccc.company.dao.CompanyAdDAO; // 기업홍보 목록 DAO를 사용하기 위해 가져온다.
import com.ccc.company.dto.CompanyAdDTO; // 페이징 정보 DTO를 사용하기 위해 가져온다.
import com.ccc.company.dto.CompanyCardDTO; // 기업 카드 DTO를 사용하기 위해 가져온다.

public class CompanyAdController implements Execute { // 기업홍보 목록 페이지를 처리하는 컨트롤러 클래스이다.

	@Override // Execute 인터페이스의 execute 메소드를 재정의한다.
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 기업홍보 목록 요청을 실제로 처리하는 메소드이다.

		CompanyAdDAO companyAdDAO = new CompanyAdDAO(); // 기업홍보 목록 조회용 DAO 객체를 생성한다.
		CompanyAdDTO companyAdDTO = new CompanyAdDTO(); // 페이지 정보를 담을 DTO 객체를 생성한다.
		CompanyCardDTO companyCardDTO = new CompanyCardDTO(); // 목록 조회 조건과 결과를 담을 DTO 객체를 생성한다.
		Result result = new Result(); // 최종 이동 정보를 담을 Result 객체를 생성한다.

		String pageParameter = request.getParameter("page"); // 요청 파라미터에서 page 값을 문자열로 가져온다.
		String jobNumberParameter = request.getParameter("jobNumber"); // 요청 파라미터에서 jobNumber 값을 문자열로 가져온다.
		String sortTypeParameter = request.getParameter("sortType"); // 요청 파라미터에서 sortType 값을 문자열로 가져온다.

		int page = 1; // 기본 페이지 번호를 1로 설정한다.
		int rowCount = 9; // 한 페이지에 보여줄 카드 개수를 9개로 설정한다.
		int pageCount = 5; // 페이지네이션에서 한 번에 보여줄 페이지 번호 개수를 5개로 설정한다.
		int totalCount = 0; // 전체 기업 개수를 저장할 변수를 선언한다.
		int realEndPage = 1; // 실제 마지막 페이지 번호를 저장할 변수를 선언한다.
		int startRow = 1; // 현재 페이지의 시작 행 번호를 저장할 변수를 선언한다.
		int endRow = 9; // 현재 페이지의 끝 행 번호를 저장할 변수를 선언한다.
		Integer jobNumber = null; // 직군 필터 번호를 저장할 변수를 선언한다.

		try { // page 값을 숫자로 변환하는 구간이다.
			if (pageParameter != null && !pageParameter.trim().isEmpty()) { // page 값이 있고 빈 문자열이 아닐 때만 처리한다.
				page = Integer.parseInt(pageParameter); // 문자열 page 값을 정수형으로 변환한다.
			}
		} catch (NumberFormatException e) { // page 값이 숫자가 아닐 때 실행되는 예외 처리 구간이다.
			page = 1; // 잘못된 값이 들어오면 1페이지로 보정한다.
		}

		try { // jobNumber 값을 숫자로 변환하는 구간이다.
			if (jobNumberParameter != null && !jobNumberParameter.trim().isEmpty()) { // jobNumber 값이 있고 빈 문자열이 아닐 때만 처리한다.
				jobNumber = Integer.valueOf(jobNumberParameter); // 문자열 jobNumber 값을 Integer로 변환한다.
			}
		} catch (NumberFormatException e) { // jobNumber 값이 숫자가 아닐 때 실행되는 예외 처리 구간이다.
			jobNumber = null; // 잘못된 값이 들어오면 직군 필터를 사용하지 않도록 null로 처리한다.
		}

		if (jobNumber != null && (jobNumber < 1 || jobNumber > 4)) { // 직군 번호가 1~4 범위를 벗어나는지 확인한다.
			jobNumber = null; // 범위를 벗어나면 필터 없이 전체 조회로 처리한다.
		}

		if (sortTypeParameter == null || sortTypeParameter.trim().isEmpty()) { // 정렬값이 없을 때 실행되는 구간이다.
			sortTypeParameter = "latest"; // 기본 정렬값을 최신순으로 설정한다.
		}

		if (!"latest".equals(sortTypeParameter) && !"name".equals(sortTypeParameter)) { // 현재 지원하지 않는 정렬값이 들어왔는지 확인한다.
			sortTypeParameter = "latest"; // 지원하지 않는 정렬값은 최신순으로 보정한다.
		}

		companyCardDTO.setJobNumber(jobNumber); // 직군 필터 번호를 목록 조회 DTO에 저장한다.
		companyCardDTO.setSortType(sortTypeParameter); // 정렬 방식을 목록 조회 DTO에 저장한다.

		totalCount = companyAdDAO.selectCompanyAdCount(companyCardDTO); // 현재 필터 조건에 맞는 전체 기업 개수를 조회한다.
		realEndPage = (int) Math.ceil(totalCount / (double) rowCount); // 전체 개수를 기준으로 실제 마지막 페이지 번호를 계산한다.

		if (realEndPage == 0) { // 조회 결과가 하나도 없어서 마지막 페이지가 0이 되는 경우이다.
			realEndPage = 1; // 페이지 계산이 깨지지 않도록 1로 보정한다.
		}

		if (page < 1) { // 현재 페이지 번호가 1보다 작은지 확인한다.
			page = 1; // 1보다 작으면 1페이지로 보정한다.
		}

		if (page > realEndPage) { // 현재 페이지 번호가 실제 마지막 페이지보다 큰지 확인한다.
			page = realEndPage; // 마지막 페이지보다 크면 실제 마지막 페이지로 보정한다.
		}

		startRow = (page - 1) * rowCount + 1; // 현재 페이지의 시작 행 번호를 계산한다.
		endRow = page * rowCount; // 현재 페이지의 끝 행 번호를 계산한다.

		companyCardDTO.setStartRow(startRow); // 계산한 시작 행 번호를 목록 조회 DTO에 저장한다.
		companyCardDTO.setEndRow(endRow); // 계산한 끝 행 번호를 목록 조회 DTO에 저장한다.

		companyAdDTO.setPage(page); // 현재 페이지 번호를 페이지 DTO에 저장한다.
		companyAdDTO.setRowCount(rowCount); // 한 페이지당 카드 개수를 페이지 DTO에 저장한다.
		companyAdDTO.setTotalCount(totalCount); // 전체 기업 개수를 페이지 DTO에 저장한다.

		companyAdDTO.setEndPage((int) (Math.ceil(page / (double) pageCount) * pageCount)); // 현재 페이지 구간의 끝 페이지 번호를 계산해서 저장한다.
		companyAdDTO.setStartPage(companyAdDTO.getEndPage() - (pageCount - 1)); // 현재 페이지 구간의 시작 페이지 번호를 계산해서 저장한다.

		if (companyAdDTO.getEndPage() > realEndPage) { // 계산된 끝 페이지가 실제 마지막 페이지보다 큰지 확인한다.
			companyAdDTO.setEndPage(realEndPage); // 실제 마지막 페이지를 넘지 않도록 보정한다.
		}

		companyAdDTO.setPrev(companyAdDTO.getStartPage() > 1); // 이전 페이지 구간이 있으면 prev를 true로 저장한다.
		companyAdDTO.setNext(companyAdDTO.getEndPage() < realEndPage); // 다음 페이지 구간이 있으면 next를 true로 저장한다.

		List<CompanyCardDTO> companyAdList = companyAdDAO.selectCompanyAdList(companyCardDTO); // 현재 페이지에 보여줄 기업 카드 목록을 조회한다.

		request.setAttribute("companyAdList", companyAdList); // 조회한 기업 카드 목록을 request 영역에 저장한다.
		request.setAttribute("companyAdDTO", companyAdDTO); // 계산한 페이지 정보를 request 영역에 저장한다.
		request.setAttribute("companyCardQuery", companyCardDTO); // 현재 조회 조건을 request 영역에 저장한다.

		System.out.println("=== CompanyAdController 실행 ==="); // 컨트롤러 실행 로그를 출력한다.
		System.out.println("companyCardQuery : " + companyCardDTO); // 조회 조건 DTO 상태를 출력한다.
		System.out.println("companyAdDTO : " + companyAdDTO); // 페이지 DTO 상태를 출력한다.
		System.out.println("companyAdList : " + companyAdList); // 조회된 목록을 출력한다.

		result.setPath("/app/main/company/company-ad.jsp"); // 최종 이동할 JSP 경로를 설정한다.
		result.setRedirect(false); // request에 담은 데이터를 JSP에서 써야 하므로 forward 방식으로 설정한다.

		return result; // 최종 이동 정보를 반환한다.
	}
}