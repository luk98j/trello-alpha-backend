package com.alpha.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Data
public class ListOfUsernameResponseDto {
    private List<String> listOfUsernames;
}
