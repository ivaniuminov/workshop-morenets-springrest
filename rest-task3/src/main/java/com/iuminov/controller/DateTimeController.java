package com.iuminov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("hello")
@ResponseBody
public class DateTimeController {

    @RequestMapping("time")
    public String currentTime() {
        return LocalTime.now().toString();
    }

    @RequestMapping("date")
    public String currentDate() {
        return LocalDate.now().toString();
    }
}
