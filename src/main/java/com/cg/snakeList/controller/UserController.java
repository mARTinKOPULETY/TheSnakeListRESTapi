package com.cg.snakeList.controller;

import com.cg.snakeList.entity.*;
import com.cg.snakeList.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllUsers(){return userService.getAllUsers();}

    @PostMapping
    @ResponseBody
    public void addUser(@RequestParam String userConfirmPassword,
                        @RequestBody User user

    ){userService.addUser( userConfirmPassword,user);}

    @PutMapping("{id}")
    public void updateUser(@RequestParam String userConfirmPassword,
                           @PathVariable Long id,
                            @RequestParam (required = false)String userName,
                            @RequestParam(required = false)String userPassword
                           )  {
        userService.updateUser(userConfirmPassword,id, userName,userPassword);

    }
    @GetMapping("/login")
    public void loginUser(@RequestParam(required = false)Long userId,

                          @RequestParam (required = false)String userName,
                          @RequestParam(required = false)String userPassword,
                          User user)  {
        userService.loginUser(userId, userName,userPassword, user);
    }
    @GetMapping("/logout")
    public void logout() {
        userService.logoutUser();
        }

    @DeleteMapping("/deleteAllUsers")
    public void deleteAllUsers(){userService.deleteAllUsers();}

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id)  {userService.deleteUser(id);}

}
