package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TrelloTableResponse {
    private Long id;
    private String title;
    private String owner;
}
