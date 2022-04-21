package com.alpha.trello.controller;

import com.alpha.trello.business.TrelloTableService;
import com.alpha.trello.dto.TrelloTableRequest;
import com.alpha.trello.dto.UserNameRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("rest/api/private/trello")
public class TrelloController {

    private TrelloTableService trelloTableService;

    @Autowired
    public TrelloController(TrelloTableService trelloTableService) {
        this.trelloTableService = trelloTableService;
    }

    @PostMapping("/add-table")
    public ResponseEntity<?> addTable(@RequestBody TrelloTableRequest trelloTableRequest){
        return trelloTableService.createTable(trelloTableRequest);
    }

    @GetMapping("/get-table")
    public ResponseEntity<?> getTable(@RequestParam("username") String userNameRequest){
        return trelloTableService.getAllTable(userNameRequest);
    }

}
