package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrelloTodoTaskRequest {
    private Long cardId;
    private Long todoTaskId;
    private String Name;
    private Boolean checked;
}
