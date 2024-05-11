package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.entity.Admin;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repo.AdminRepo;
import com.example.employeemanagement.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDTO responseDTO;

    public ResponseEntity saveAdmin(AdminDTO adminDTO){
        try{
            if (adminRepo.existsById(adminDTO.getAdminId())) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Admin Registered");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                adminRepo.save(modelMapper.map(adminDTO, Admin.class));
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
