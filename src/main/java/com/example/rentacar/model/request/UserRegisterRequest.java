package com.example.rentacar.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    @NotBlank
    @Size(min = 3,max = 8)
    String username;
    @NotBlank
    @Size(min = 3)
    String password;
    @NotBlank
    @Pattern(regexp = "^(?:\\+994|0)?(10|12|2[0-9]|36|50|51|55|70|77|99)\\d{7}$")
    String number;
    @NotBlank
    @Email
    String email;

}
