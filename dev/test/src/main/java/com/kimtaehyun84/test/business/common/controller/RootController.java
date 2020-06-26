package com.kimtaehyun84.test.business.common.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.time.LocalDateTime;

import java.util.Locale;

@Controller
public class RootController {
    private static final Logger logger = LoggerFactory.getLogger(RootController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws IOException {


        logger.info("Welcome home! The client locale is {}.", locale);


        model.addAttribute("serverTime", LocalDateTime.now());

        return "index";
    }
}
