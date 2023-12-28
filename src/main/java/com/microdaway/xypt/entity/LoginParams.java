package com.microdaway.xypt.entity;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginParams {
    @Pattern(regexp = "^\\S{5,15}$")
    private String username;
    @Pattern(regexp = "^\\S{5,15}$")
    private String password;
}
