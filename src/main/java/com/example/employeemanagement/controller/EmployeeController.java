package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
      //  ResponseEntity res=employeeService.saveEmployee(employeeDTO);
      //      return res;
        return new ResponseEntity(employeeService.saveEmployee(employeeDTO), HttpStatus.ACCEPTED);

    }
    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity(employeeService.updateEmployee(employeeDTO),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(){
            return employeeService.getAllEmployee();
    }

    @GetMapping("/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID){
            return employeeService.searchEmployee(empID);
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID){
            ResponseEntity res = employeeService.deleteEmployee(empID);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }

}
