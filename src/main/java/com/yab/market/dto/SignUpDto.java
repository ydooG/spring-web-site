package com.yab.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String surname;

    @NotEmpty
    @Size(min = 8, max = 255)
    private  String password;

    @NotEmpty
    @Email
    private String email;
}
