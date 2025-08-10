package com.mayur.FlipCommerce.Controller;

import com.mayur.FlipCommerce.DTOs.UserDto;
import com.mayur.FlipCommerce.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
