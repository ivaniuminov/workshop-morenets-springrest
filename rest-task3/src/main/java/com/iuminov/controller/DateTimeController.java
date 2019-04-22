package com.iuminov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("current")
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
