package com.ccc.expo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

public class ExpoFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ExpoFrontController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("ExpoFrontController 실행!!");

        String target = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("현재 경로 : " + target);

        Result result = null;

        switch (target) {
        case "/expo/calendar.ex":
            System.out.println("박람회 페이지 진입");
            result = new ExpoCalendarController().execute(request, response);
            break;
            
        case "/expo/list.ex":
            System.out.println("박람회 일정 목록 요청");
            result = new ExpoListOkController().execute(request, response);
            break;

        case "/expo/detail.ex":
            System.out.println("박람회 상세 요청");
            result = new ExpoDetailOkController().execute(request, response);
            break;
        }

        if (result != null && result.getPath() != null) {
            if (result.isRedirect()) {
                response.sendRedirect(result.getPath());
            } else {
                request.getRequestDispatcher(result.getPath()).forward(request, response);
            }
        }
    }
}
