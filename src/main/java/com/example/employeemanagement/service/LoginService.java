package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.entity.Admin;
import com.example.employeemanagement.repo.AdminRepo;
import com.example.employeemanagement.util.VarList;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class LoginService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity signIn(AdminDTO adminDTO){
        Admin admin = adminRepo.findByUserName(adminDTO.getUserName());
  log.error("vvvvvvvvvv {}",admin);
        if (admin != null && passwordEncoder.matches(adminDTO.getPassword(), admin.getPassword())) {
            // Authentication successful
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            // Authentication failed
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
