package com.ccc.expo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

public class ExpoCalendarController {

    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        result.setPath("/app/main/expo/expo-calendar.jsp");
        result.setRedirect(false);
        return result;
    }
}