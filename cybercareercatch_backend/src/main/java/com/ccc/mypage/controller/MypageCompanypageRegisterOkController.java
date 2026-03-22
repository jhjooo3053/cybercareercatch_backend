package com.ccc.mypage.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyInfoDTO;
import com.ccc.company.dto.FileDTO;
import com.ccc.company.dto.JobPostDTO;
import com.ccc.mypage.dao.MypageDAO;
import com.ccc.mypage.dto.CompanyMypageInfoDTO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class MypageCompanypageRegisterOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageCompanypageRegisterOkController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		
		//테스트용 - 삭제
		session.setAttribute("userNumber", 51);
		session.setAttribute("userType", "기업회원");
		
		System.out.println("로그인한 회원 번호 : " + userNumber);
		System.out.println("로그인한 회원 타입 : " + userType);
		
		// 로그인 안 된 경우
		if (userNumber == null) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/member/login.mefc");
			return result;
		}

		// 기업회원이 아니면 접근 불가
		if (userType == null || !userType.equals("기업회원")) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			return result;
		}
		
		// 이미 기업정보페이지가 있으면 수정페이지로 보냄
		int companyPageCount = mypageDAO.countCompanyPageByUserNumber(userNumber);
		if (companyPageCount > 0) {
			result.setPath(request.getContextPath() + "/company/mypage/companypageEdit.mpfc");
			result.setRedirect(true);
			return result;
		}

		// 로그인한 기업회원의 회사번호 조회
		CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
		if (companyMypageInfoDTO == null) {
			result.setPath(request.getContextPath() + "/company/mypage.mpfc");
			result.setRedirect(true);
			return result;
		}

		int companyNumber = companyMypageInfoDTO.getCompanyNumber();

		// 실제 업로드 경로
		final String UPLOAD_PATH = request.getSession()
				.getServletContext()
				.getRealPath("/") + "upload/company/";

		// 최대 5MB
		final int FILE_SIZE = 1024 * 1024 * 5;

		File uploadDir = new File(UPLOAD_PATH);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		MultipartParser parser = new MultipartParser(request, FILE_SIZE);
		parser.setEncoding("utf-8");
		System.out.println("MultipartParser 초기화 완료");

		CompanyInfoDTO companyInfoDTO = new CompanyInfoDTO();
		companyInfoDTO.setCompanyNumber(companyNumber);

		JobPostDTO jobPostDTO = new JobPostDTO();
		jobPostDTO.setCompanyNumber(companyNumber);

		// 체크박스는 선택 안 되면 값이 안 넘어오므로 기본 0
		jobPostDTO.setCat1IsHiring(0);
		jobPostDTO.setCat2IsHiring(0);
		jobPostDTO.setCat3IsHiring(0);
		jobPostDTO.setCat4IsHiring(0);

		String newFilePath = null;

		Part part;
		while ((part = parser.readNextPart()) != null) {
			System.out.println("Part : " + part.getClass().getSimpleName());

			if (part.isParam()) {
				ParamPart paramPart = (ParamPart) part;
				String paramName = paramPart.getName();
				String paramValue = paramPart.getStringValue();

				System.out.println("파라미터 : " + paramName + " = " + paramValue);

				// 기업 기본정보
				if ("company_ceoname".equals(paramName)) {
					companyInfoDTO.setCompCeoName(paramValue);

				} else if ("company_yearfounded".equals(paramName)) {
					companyInfoDTO.setCompFndYear(parseIntOnlyNumber(paramValue));

				} else if ("company_employeecount".equals(paramName)) {
					companyInfoDTO.setCompEmplCnt(parseIntOnlyNumber(paramValue));

				} else if ("company_sales".equals(paramName)) {
				    companyInfoDTO.setCompRev(parseLongOnlyNumber(paramValue));

				} else if ("company_capital".equals(paramName)) {
				    companyInfoDTO.setCompCap(parseLongOnlyNumber(paramValue));

				} else if ("company_type".equals(paramName)) {
					companyInfoDTO.setCompType(paramValue);

				// 기업 정보
				} else if ("company_description".equals(paramName)) {
					companyInfoDTO.setCompSummary(paramValue);

				} else if ("company_info".equals(paramName)) {
					companyInfoDTO.setCompInfo(paramValue);

				} else if ("company_tech".equals(paramName)) {
					companyInfoDTO.setCompTech(paramValue);

				} else if ("company_business".equals(paramName)) {
					companyInfoDTO.setCompMainBiz(paramValue);

				} else if ("company_history".equals(paramName)) {
					companyInfoDTO.setCompSvcHist(paramValue);

				// 채용공고
				} else if ("company_talent".equals(paramName)) {
					jobPostDTO.setJobPostProfile(paramValue);

				} else if ("company_jobpart".equals(paramName)) {
					jobPostDTO.setJobPostContent(paramValue);

				} else if ("company_process".equals(paramName)) {
					jobPostDTO.setJobPostProcess(paramValue);

				} else if ("company_apply".equals(paramName)) {
					jobPostDTO.setJobPostMethod(paramValue);

				} else if ("job_group".equals(paramName)) {
					if ("1".equals(paramValue)) {
						jobPostDTO.setCat1IsHiring(1);
					} else if ("2".equals(paramValue)) {
						jobPostDTO.setCat2IsHiring(1);
					} else if ("3".equals(paramValue)) {
						jobPostDTO.setCat3IsHiring(1);
					} else if ("4".equals(paramValue)) {
						jobPostDTO.setCat4IsHiring(1);
					}
				}

			} else if (part.isFile()) {
				FilePart filePart = (FilePart) part;
				filePart.setRenamePolicy(new DefaultFileRenamePolicy());

				String fileOriginalName = filePart.getFileName();

				if (fileOriginalName != null && !fileOriginalName.trim().isEmpty()) {
					String newFileName = System.currentTimeMillis() + "_" + fileOriginalName;
					File newFile = new File(UPLOAD_PATH, newFileName);

					filePart.writeTo(newFile);

					if (newFile.exists()) {
						System.out.println("새 파일 저장 완료 : " + newFile.getAbsolutePath());
					} else {
						System.out.println("새 파일 저장 실패 : " + newFile.getAbsolutePath());
					}

					newFilePath = "upload/company/" + newFileName;
				}
			}
		}

		// 기업소개 등록
		mypageDAO.insertCompInfo(companyInfoDTO);
		System.out.println("TBL_COMP_INFO 등록 완료");

		// 채용공고 등록
		mypageDAO.insertJobPost(jobPostDTO);
		System.out.println("TBL_JOB_POST 등록 완료");

		// 파일 등록
		if (newFilePath != null) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setCompanyNumber(companyNumber);
			fileDTO.setFilePath(newFilePath);

			mypageDAO.insertFile(fileDTO);
			System.out.println("TBL_FILE 등록 완료");
		}

		result.setPath(request.getContextPath() + "/company/mypage.mpfc?registerSuccess=true");
		result.setRedirect(true);
		return result;
		
	}

	// 문자열에서 숫자만 추출해서 int로 변환
	private int parseIntOnlyNumber(String value) {
		try {
			if (value == null || value.trim().isEmpty()) {
				return 0;
			}

			String onlyNumber = value.replaceAll("[^0-9]", "");

			if (onlyNumber.isEmpty()) {
				return 0;
			}

			return Integer.parseInt(onlyNumber);

		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	private long parseLongOnlyNumber(String value) {
	    try {
	        if (value == null || value.trim().isEmpty()) {
	            return 0L;
	        }

	        String onlyNumber = value.replaceAll("[^0-9]", "");

	        if (onlyNumber.isEmpty()) {
	            return 0L;
	        }

	        return Long.parseLong(onlyNumber);

	    } catch (NumberFormatException e) {
	        return 0L;
	    }
	}
}