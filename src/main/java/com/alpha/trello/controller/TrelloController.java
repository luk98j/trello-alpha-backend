package com.alpha.trello.controller;

import com.alpha.trello.business.TrelloCardService;
import com.alpha.trello.business.TrelloCommentService;
import com.alpha.trello.business.TrelloTodoService;
import com.alpha.trello.business.TrelloTodoTaskService;
import com.alpha.trello.business.TrelloTableService;
import com.alpha.trello.business.TrelloListService;
import com.alpha.trello.dto.*;
import com.alpha.trello.entity.TrelloTodoTask;
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
    private TrelloCommentService trelloCommentService;
    private TrelloTodoService trelloTodoService;
    private TrelloTodoTaskService trelloTodoTaskService;

    @Autowired
    public TrelloController(TrelloTableService trelloTableService, TrelloListService trelloListService,
                            TrelloCardService trelloCardService, TrelloCommentService trelloCommentService,
                            TrelloTodoService trelloTodoService, TrelloTodoTaskService trelloTodoTaskService) {
        this.trelloTableService = trelloTableService;
        this.trelloListService = trelloListService;
        this.trelloCardService = trelloCardService;
        this.trelloCommentService = trelloCommentService;
        this.trelloTodoService = trelloTodoService;
        this.trelloTodoTaskService = trelloTodoTaskService;
    }

    @PostMapping("/add-table")
    public ResponseEntity<?> addTable(@RequestBody TrelloTableRequest trelloTableRequest) {
        return trelloTableService.createTable(trelloTableRequest);
    }

    @GetMapping("/get-table")
    public ResponseEntity<?> getTable(@RequestParam("username") String userNameRequest) {
        return trelloTableService.getAllTable(userNameRequest);
    }

    @GetMapping("/get-list")
    public ResponseEntity<?> getList(@RequestParam("id") Long idRequest) {
        return trelloListService.getList(idRequest);
    }

    @PostMapping("/add-list")
    public ResponseEntity<?> addList(@RequestBody TrelloListRequest trelloListRequest) {
        return trelloListService.addList(trelloListRequest);
    }

    @GetMapping("/get-info-table")
    public ResponseEntity<?> getInfoAboutTable(@RequestParam("id") Long idRequest) {
        return trelloTableService.getInfoAboutTable(idRequest);
    }

    @PostMapping("/add-card")
    public ResponseEntity<?> addCard(@RequestBody TrelloCardRequest trelloCardRequest) {
        return trelloCardService.addCard(trelloCardRequest);
    }

    @PostMapping("/edit-card")
    public ResponseEntity<?> editCard(@RequestBody TrelloCardRequest trelloCardRequest) {
        return trelloCardService.editCard(trelloCardRequest);
    }

    @GetMapping("/get-cards")
    public ResponseEntity<?> getAllCards(@RequestParam("id") Long id) {
        return trelloCardService.getAllCards(id);
    }

    @GetMapping("/get-comments")
    public ResponseEntity<?> getAllComments(@RequestParam("id") Long id) {
        return trelloCommentService.getAllComments(id);
    }

    @PostMapping("/add-comment")
    public ResponseEntity<?> addComment(@RequestBody TrelloCommentRequest trelloCommentRequest) {
        return trelloCommentService.addComment(trelloCommentRequest);
    }
    @PostMapping("/add-todo")
    public ResponseEntity<?> addTodo(@RequestBody TrelloTodoRequest trelloTodoRequest) {
        return trelloTodoService.addTodo(trelloTodoRequest);
    }
    @GetMapping("/get-todos")
    public ResponseEntity<?> getAllTodos(@RequestParam("id") Long id) {
        return trelloTodoService.getAllTodos(id);
    }

    @GetMapping("/get-tasks")
    public ResponseEntity<?> getAllTasks(@RequestParam("id") Long id) {
        return trelloTodoTaskService.getAllTodoTasks(id);
    }
}