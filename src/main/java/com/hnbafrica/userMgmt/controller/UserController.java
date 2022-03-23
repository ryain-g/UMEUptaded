package com.hnbafrica.userMgmt.controller;


import com.hnbafrica.userMgmt.dto.User;
import com.hnbafrica.userMgmt.entity.UserEntity;
import com.hnbafrica.userMgmt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;


@RestController
@RequestMapping(path="/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    SpringTemplateEngine springTemplateEngine;


    @PostMapping(path="/addUser")
    public void addNewUser (@RequestBody User user) {
        userService.addUserAndSendEmail(user);
    }


    @GetMapping(path="/allUsers")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.getUserList();
    }


    @GetMapping(path="/getUser/{name}")
    @ResponseBody
    public UserEntity getUserByName(@PathVariable ("name") String name) {
        return userService.getUserByFirstName(name);
    }


    @GetMapping(path="/deleteUser/{name}")
    @ResponseBody
    public void updateUserByName(@PathVariable ("name") String name) {
         userService.updateUserByFirstName(name);
    }

    @GetMapping(path="/confirmCode/{code}")
    @ResponseBody
    public int confirmUser(@PathVariable ("code") String code) {
        log.info("Enable user with code , {}",code);
        return  userService.enableUser(code);
    }
}
