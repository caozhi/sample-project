package com.green.controller;

import com.green.utl.SystemLogger;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/v1")
public class EchoController {
    private Logger logger = SystemLogger.getLogger(EchoController.class);

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public String echo(@RequestParam(required = false, value = "word") String word, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        logger.info("call echo");
        if (word != null && word.equals("")) {
            return "Hello " + word + " !";
        }
        return "Hello world!";
    }

}
