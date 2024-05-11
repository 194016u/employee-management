package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveAdmin")
    public ResponseEntity saveAdmin(@RequestBody AdminDTO adminDTO){
        //  ResponseEntity res=adminService.saveAdmin(adminDTO);
        //      return res;
        return new ResponseEntity(adminService.saveAdmin(adminDTO), HttpStatus.ACCEPTED);

    }


}
