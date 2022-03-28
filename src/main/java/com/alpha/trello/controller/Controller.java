package com.alpha.trello.controller;

import com.alpha.trello.repository.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class Controller {
    @Autowired
    private TempRepository tempRepository;

    @GetMapping("/hello")
    public String getHello() {
        return tempRepository.findAll().get(0).getName();
    }

}
