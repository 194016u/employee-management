package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping(value = "signIn")
    public ResponseEntity signIn(@RequestBody AdminDTO adminDTO){
        return new ResponseEntity(loginService.signIn(adminDTO), HttpStatus.ACCEPTED);
    }
}
