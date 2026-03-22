package com.ccc.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.company.dto.CompanyInfoDTO;
import com.ccc.company.dto.FileDTO;
import com.ccc.company.dto.JobPostDTO;
import com.ccc.mypage.dao.MypageDAO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class MypageCompanypageEditOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageCompanypageEditOkController 실행");

		// 마이페이지 관련 DB 작업을 처리할 DAO
		MypageDAO mypageDAO = new MypageDAO();

		// 이동할 경로와 redirect 여부를 담는 객체
		Result result = new Result();

		// 로그인 사용자 확인을 위한 세션 객체
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

		// 기존 기업정보페이지 상세정보 조회
		// 현재 mapper는 userNumber 기준으로 조회하도록 되어 있음
		CompanyDetailDTO existingDetail = mypageDAO.selectCompanyPageDetail(userNumber);

		// 기존 기업정보페이지가 없는 경우 수정할 수 없으므로 등록 페이지로 이동
		if (existingDetail == null) {
			System.out.println("기존 기업정보페이지 없음");
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/company/mypage/companypageRegister.mpfc");
			return result;
		}

		// 기존 데이터에서 회사번호 추출
		int companyNumber = existingDetail.getCompanyNumber();

		// 실제 업로드 파일이 저장될 서버 폴더 경로
		final String UPLOAD_PATH = request.getSession()
				.getServletContext()
				.getRealPath("/") + "upload/company/";

		// 최대 업로드 크기 : 5MB
		final int FILE_SIZE = 1024 * 1024 * 5;

		// 업로드 폴더가 없으면 생성
		File uploadDir = new File(UPLOAD_PATH);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// multipart/form-data 요청을 직접 파싱하기 위한 객체
		// 선생님 게시판 수정 방식과 같은 구조
		MultipartParser parser = new MultipartParser(request, FILE_SIZE);
		parser.setEncoding("utf-8");
		System.out.println("MultipartParser 초기화 완료");

		// 기업소개 수정용 DTO
		CompanyInfoDTO companyInfoDTO = new CompanyInfoDTO();
		companyInfoDTO.setCompanyNumber(companyNumber);

		// 채용공고 수정용 DTO
		JobPostDTO jobPostDTO = new JobPostDTO();
		jobPostDTO.setCompanyNumber(companyNumber);

		// 체크박스는 선택되지 않으면 값이 아예 넘어오지 않기 때문에
		// 미리 0으로 세팅하고 선택된 것만 1로 변경
		jobPostDTO.setCat1IsHiring(0);
		jobPostDTO.setCat2IsHiring(0);
		jobPostDTO.setCat3IsHiring(0);
		jobPostDTO.setCat4IsHiring(0);

		// 새로 업로드된 파일 경로를 저장할 변수
		// 파일을 새로 올리지 않으면 null 유지
		String newFilePath = null;

		Part part;

		// multipart 요청 안의 데이터들을 하나씩 꺼내서 처리
		while ((part = parser.readNextPart()) != null) {
			System.out.println("Part : " + part.getClass().getSimpleName());

			// 일반 텍스트 파라미터 처리
			if (part.isParam()) {
				ParamPart paramPart = (ParamPart) part;

				// input/textarea/checkbox의 name 값
				String paramName = paramPart.getName();

				// 사용자가 실제로 입력한 값
				String paramValue = paramPart.getStringValue();

				System.out.println("파라미터 : " + paramName + " = " + paramValue);

				// ===== 기업 기본정보 =====

				if ("company_ceoname".equals(paramName)) {
					// 대표자명
					companyInfoDTO.setCompCeoName(paramValue);

				} else if ("company_yearfounded".equals(paramName)) {
					// 설립년도
					companyInfoDTO.setCompFndYear(parseIntOnlyNumber(paramValue));

				} else if ("company_employeecount".equals(paramName)) {
					// 사원수
					companyInfoDTO.setCompEmplCnt(parseIntOnlyNumber(paramValue));

				} else if ("company_sales".equals(paramName)) {
				    companyInfoDTO.setCompRev(parseLongOnlyNumber(paramValue));

				} else if ("company_capital".equals(paramName)) {
				    companyInfoDTO.setCompCap(parseLongOnlyNumber(paramValue));

				} else if ("company_type".equals(paramName)) {
					// 기업형태
					companyInfoDTO.setCompType(paramValue);

				// ===== 기업 정보 =====

				} else if ("company_description".equals(paramName)) {
					// 한줄소개
					companyInfoDTO.setCompSummary(paramValue);

				} else if ("company_info".equals(paramName)) {
					// 기업정보(상세 소개)
					companyInfoDTO.setCompInfo(paramValue);

				} else if ("company_tech".equals(paramName)) {
					// 대표기술
					companyInfoDTO.setCompTech(paramValue);

				} else if ("company_business".equals(paramName)) {
					// 주요사업
					companyInfoDTO.setCompMainBiz(paramValue);

				} else if ("company_history".equals(paramName)) {
					// 서비스 확장 및 운영 이력
					companyInfoDTO.setCompSvcHist(paramValue);

				// ===== 채용공고 =====

				} else if ("company_talent".equals(paramName)) {
					// 인재상
					jobPostDTO.setJobPostProfile(paramValue);

				} else if ("company_jobpart".equals(paramName)) {
					// 채용부분
					jobPostDTO.setJobPostContent(paramValue);

				} else if ("company_process".equals(paramName)) {
					// 채용 절차
					jobPostDTO.setJobPostProcess(paramValue);

				} else if ("company_apply".equals(paramName)) {
					// 지원 정보
					jobPostDTO.setJobPostMethod(paramValue);

				} else if ("job_group".equals(paramName)) {
					// 직군 체크박스
					// 같은 name(job_group)으로 여러 개가 들어오므로 value로 구분
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
				// 파일 파트 처리
				FilePart filePart = (FilePart) part;

				// 같은 파일명이 있으면 자동 변경 정책 적용
				filePart.setRenamePolicy(new DefaultFileRenamePolicy());

				// 사용자가 선택한 원본 파일명
				String fileOriginalName = filePart.getFileName();

				// 새 파일을 실제로 선택한 경우에만 처리
				if (fileOriginalName != null && !fileOriginalName.trim().isEmpty()) {

					// 기존 파일이 DB에 등록되어 있으면 서버에 있는 파일부터 삭제
					if (existingDetail.getFilePath() != null && !existingDetail.getFilePath().trim().isEmpty()) {
						File oldFile = new File(
								request.getSession().getServletContext().getRealPath("/"),
								existingDetail.getFilePath()
						);

						if (oldFile.exists()) {
							System.out.println("기존 파일 삭제 : " + oldFile.getAbsolutePath());
							oldFile.delete();
						}
					}

					// 새 파일명 생성
					// 시간값을 붙여서 중복 저장 가능성을 줄임
					String newFileName = System.currentTimeMillis() + "_" + fileOriginalName;

					// 실제 서버에 저장할 파일 객체 생성
					File newFile = new File(UPLOAD_PATH, newFileName);

					// 업로드 파일을 서버 디스크에 저장
					filePart.writeTo(newFile);

					if (newFile.exists()) {
						System.out.println("새 파일 저장 완료 : " + newFile.getAbsolutePath());
					} else {
						System.out.println("새 파일 저장 실패 : " + newFile.getAbsolutePath());
					}

					// DB에 저장할 상대경로
					newFilePath = "upload/company/" + newFileName;
				}
			}
		}

		// 기업소개 테이블 수정
		mypageDAO.updateCompInfo(companyInfoDTO);
		System.out.println("기업소개 수정 완료");

		// 채용공고 테이블 수정
		mypageDAO.updateJobPost(jobPostDTO);
		System.out.println("채용공고 수정 완료");

		// 파일 처리
		// 새 파일을 올린 경우에만 TBL_FILE 수정/등록
		if (newFilePath != null) {

			// 기존 파일 row가 없으면 insert
			if (existingDetail.getFilePath() == null || existingDetail.getFilePath().trim().isEmpty()) {
				FileDTO fileDTO = new FileDTO();
				fileDTO.setCompanyNumber(companyNumber);
				fileDTO.setFilePath(newFilePath);

				mypageDAO.insertFile(fileDTO);
				System.out.println("TBL_FILE 신규 insert 완료");

			} else {
				// 기존 파일 row가 있으면 update
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("userNumber", userNumber);
				paramMap.put("filePath", newFilePath);

				mypageDAO.updateFileByUserNumber(paramMap);
				System.out.println("TBL_FILE update 완료");
			}
		}

		// 수정 완료 후 기업회원 마이페이지로 이동
		result.setPath(request.getContextPath() + "/company/mypage.mpfc?editSuccess=true");
		result.setRedirect(true);
		return result;
	}


	//문자열에서 숫자만 뽑아서 int로 바꾸는 메소드
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