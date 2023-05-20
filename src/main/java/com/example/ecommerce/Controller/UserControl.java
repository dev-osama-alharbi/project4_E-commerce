package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControl {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> users=userService.getUser();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(" User Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user ,Errors errors ,@PathVariable int id){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate=userService.updateUser(id, user);
        if(isUpdate){
            return ResponseEntity.status(200).body("User Update");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean isDelete=userService.deleteUser(id);
        if(isDelete){
            return ResponseEntity.status(200).body("User Deleted");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

}
