package com.kube.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class First {
    @GetMapping("/hello")
    public String sayHi(@RequestParam String name) {
        return "HI " + name;
    }
}
