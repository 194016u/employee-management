package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getAllAdmins")
    public ResponseEntity getAllAdmin(){
        return adminService.getAllAdmin();
    }
    @PutMapping(value = "/updateAdmin")
    public ResponseEntity updateAdmin(@RequestBody AdminDTO adminDTO){
        return new ResponseEntity(adminService.updateAdmin(adminDTO),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteAdmin/{empID}")
    public ResponseEntity deleteAdmin(@PathVariable int adminID){
        ResponseEntity res = adminService.deleteAdmin(adminID);
        return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }



}
