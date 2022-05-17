package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrelloCardRequest {
    private Long listId;
    private Long cardId;
    private String title;
    private String description;
}