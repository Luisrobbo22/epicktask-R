package br.com.fiap.epictask.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank(message = "{user.name.blank}")
    private String name;

    @NotBlank(message = "{user.email.blank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.github.blank}")
    private String githubuser;
}
