package com.sample.controller;

import com.sample.util.SystemLogger;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/v1")
public class StatusCheck {
    private Logger logger = SystemLogger.getLogger(StatusCheck.class);

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public String echo(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        logger.info("call status");
        return "OK";
    }

}
