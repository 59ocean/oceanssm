package com.ocean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String toIndex(){
        System.out.println("shoueye++++++++++++++++++++++++=");
        System.out.println("shoueye222++++++++++++++++++++++++=");
        return "test";
    }
    @RequestMapping("/index2")
    public String toIndex2(){
        System.out.println("shoueye33333333333333++++++++++++++++++++++++=");
        System.out.println("shoueye222++++++++++++++++++++++++=");
        return "test";
    }
    @RequestMapping("/")
    public String shouye(){
        System.out.println("shoueye++++++=============");
        System.out.println("shoueye2++++++=============");
        return "index";
    }
    @RequestMapping("toLogin")
    public String login(){
        return "login";
    }
}
