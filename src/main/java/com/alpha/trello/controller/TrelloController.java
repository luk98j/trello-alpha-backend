package com.alpha.trello.controller;

import com.alpha.trello.business.TrelloCardService;
import com.alpha.trello.business.TrelloTableService;
import com.alpha.trello.business.TrelloListService;
import com.alpha.trello.dto.TrelloCardRequest;
import com.alpha.trello.dto.TrelloTableRequest;
import com.alpha.trello.dto.TrelloListRequest;
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
    private TrelloListService trelloListService;
    private TrelloCardService trelloCardService;

    @Autowired
    public TrelloController(TrelloTableService trelloTableService, TrelloListService trelloListService, TrelloCardService trelloCardService) {
        this.trelloTableService = trelloTableService;
        this.trelloListService = trelloListService;
        this.trelloCardService = trelloCardService;
    }

    @PostMapping("/add-table")
    public ResponseEntity<?> addTable(@RequestBody TrelloTableRequest trelloTableRequest){
        return trelloTableService.createTable(trelloTableRequest);
    }

    @GetMapping("/get-table")
    public ResponseEntity<?> getTable(@RequestParam("username") String userNameRequest){
        return trelloTableService.getAllTable(userNameRequest);
    }

    @GetMapping("/get-list")
    public ResponseEntity<?> getList(@RequestParam("id") Long idRequest){
        return trelloListService.getList(idRequest);
    }

    @PostMapping("/add-list")
    public ResponseEntity<?> addList(@RequestBody TrelloListRequest trelloListRequest){
        return trelloListService.addList(trelloListRequest);
    }

    @GetMapping("/get-info-table")
    public ResponseEntity<?> getInfoAboutTable(@RequestParam("id") Long idRequest){
        return trelloTableService.getInfoAboutTable(idRequest);
    }

    @PostMapping("/add-card")
    public ResponseEntity<?> addCard(@RequestBody TrelloCardRequest trelloCardRequest){
        return trelloCardService.addCard(trelloCardRequest);
    }

    @GetMapping("/get-cards")
    public ResponseEntity<?> getAllCards(@RequestParam("id") Long id){
        return trelloCardService.getAllCards(id);
    }

}