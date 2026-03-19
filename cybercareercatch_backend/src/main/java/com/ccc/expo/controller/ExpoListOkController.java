package com.ccc.expo.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.expo.dao.ExpoDAO;
import com.ccc.expo.dto.ExpoCalendarDTO;

public class ExpoListOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExpoDAO expoDAO = new ExpoDAO();
        List<ExpoCalendarDTO> expoList = expoDAO.selectExpoCalendarList();

        response.setContentType("application/json; charset=UTF-8");

        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < expoList.size(); i++) {
            ExpoCalendarDTO expo = expoList.get(i);

            json.append("{");
            json.append("\"expoNumber\":").append(expo.getExpoNumber()).append(",");
            json.append("\"expoName\":\"").append(escapeJson(expo.getExpoName())).append("\",");
            json.append("\"startDate\":\"").append(expo.getStartDate()).append("\",");
            json.append("\"endDate\":\"").append(expo.getEndDate()).append("\"");
            json.append("}");

            if (i < expoList.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        response.getWriter().write(json.toString());
        return null;
    }

    private String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}