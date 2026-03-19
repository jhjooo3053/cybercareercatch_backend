package com.ccc.expo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.expo.dao.ExpoDAO;
import com.ccc.expo.dto.ExpoDetailDTO;

public class ExpoDetailOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String expoNumberParam = request.getParameter("expoNumber");

        response.setContentType("application/json; charset=UTF-8");

        if (expoNumberParam == null || expoNumberParam.trim().isEmpty()) {
            response.getWriter().write("{\"expoName\":\"\",\"location\":\"\",\"companies\":[]}");
            return null;
        }

        int expoNumber = Integer.parseInt(expoNumberParam);

        ExpoDAO expoDAO = new ExpoDAO();
        List<ExpoDetailDTO> detailList = expoDAO.selectExpoDetailList(expoNumber);

        if (detailList == null || detailList.isEmpty()) {
            response.getWriter().write("{\"expoName\":\"\",\"location\":\"\",\"companies\":[]}");
            return null;
        }

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"expoName\":\"").append(escapeJson(detailList.get(0).getExpoName())).append("\",");
        json.append("\"location\":\"").append(escapeJson(detailList.get(0).getLocation())).append("\",");
        json.append("\"companies\":[");

        boolean first = true;

        for (ExpoDetailDTO detail : detailList) {
            if (detail.getCompanyName() != null) {
                if (!first) {
                    json.append(",");
                }

                json.append("{");
                json.append("\"companyName\":\"").append(escapeJson(detail.getCompanyName())).append("\",");
                json.append("\"companyAddress\":\"").append(escapeJson(detail.getCompanyAddress())).append("\"");
                json.append("}");

                first = false;
            }
        }

        json.append("]");
        json.append("}");

        response.getWriter().write(json.toString());
        return null;
    }

    private String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}