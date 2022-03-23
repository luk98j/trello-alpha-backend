package com.alpha.trello;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class Controller {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }

}
