package com.blog.springs.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty(message ="Username must be minimum 4 characters")
    @Size(min=4,message ="Username must be minimum 4 characters")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotEmpty
    @Size(min=8,max=12, message="Password must contail atleast 8 to 12 characters")
    private String password;

    @NotEmpty(message ="Must not be empty")
    private String about;
}
