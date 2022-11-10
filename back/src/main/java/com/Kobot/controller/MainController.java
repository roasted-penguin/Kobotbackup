package com.Kobot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping("/login")
public class MainController {

    @GetMapping("")
    public String getParameters(@RequestParam String id, @RequestParam String password){
        return "아이디는 "+id+" 비밀번호는 "+password;
    }

    @RequestMapping(value = "trading", method = RequestMethod.GET)
    public String trading(){
        return "Trading";
    }
}
