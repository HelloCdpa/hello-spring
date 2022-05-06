package com.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//spring 컨테이너에서 빈이 관리된다.
@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "home";
    }



}
