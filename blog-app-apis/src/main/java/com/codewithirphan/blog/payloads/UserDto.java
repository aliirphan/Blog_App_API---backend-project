package com.codewithirphan.blog.payloads;


import com.codewithirphan.blog.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserDto {
    public UserDto(){}
    public UserDto(int id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 characters")
    private String name;

    @Email(message = "Email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "password must be min of 3 chars and max of 10 chars!!")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles=new HashSet<>();

}
