package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrelloListRequest {
    private String title;
    private Long tableId;
}
