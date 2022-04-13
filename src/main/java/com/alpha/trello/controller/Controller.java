package com.alpha.trello.controller;

import com.alpha.trello.repository.TempRepository;
import com.alpha.trello.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api")
public class Controller {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TempRepository tempRepository;

    @GetMapping("/hello")
    public String getHello() {
        return tempRepository.findAll().get(0).getName();
    }

    @PostMapping("/hashpassword")
    public String hashPassword(@RequestBody PasswordHash passwordHash){
        return encoder.encode(passwordHash.getPassword());
    }

}
