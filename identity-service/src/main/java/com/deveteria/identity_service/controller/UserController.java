package com.deveteria.identity_service.controller;

import com.deveteria.identity_service.dto.request.ApiResponse;
import com.deveteria.identity_service.dto.request.UserCreationRequest;
import com.deveteria.identity_service.dto.request.UserUpdateRequest;
import com.deveteria.identity_service.entity.User;
import com.deveteria.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> response = new ApiResponse<>();

        response.setResult(userService.createUser(request));

        return response;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userid}")
    User getUserID(@PathVariable("userid") String userId){
        return userService.getUserId(userId);
    }

    @PutMapping("/{userid}")
    User updateUser(@RequestBody UserUpdateRequest request, @PathVariable String userid){
        return userService.updateUser(userid, request);
    }

    @DeleteMapping("/{userid}")
    String deleteUser(@PathVariable String userid){
        userService.deleteUser(userid);
        return "User has beeen deleted";
    }
}
