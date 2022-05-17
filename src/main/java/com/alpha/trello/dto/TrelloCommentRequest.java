package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrelloCommentRequest {
    private Long cardId;
    private Long commentId;
    private Integer userId;
    private String comment;
}