package com.alpha.trello.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserNameRequest {
    @NotBlank
    private String userName;
}
