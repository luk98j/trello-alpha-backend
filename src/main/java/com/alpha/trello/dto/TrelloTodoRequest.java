package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrelloTodoRequest {
    private Long cardId;
    private Long todoId;
    private String Name;
}