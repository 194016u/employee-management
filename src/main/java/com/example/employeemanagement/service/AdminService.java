package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.entity.Admin;
import com.example.employeemanagement.repo.AdminRepo;
import com.example.employeemanagement.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity saveAdmin(AdminDTO adminDTO){
        try{
            if (adminRepo.existsById(adminDTO.getAdminId())) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Admin Registered");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                // Encrypt the password before saving
                String encryptedPassword = this.passwordEncoder.encode(adminDTO.getPassword());
                adminDTO.setPassword(encryptedPassword);
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
    public ResponseEntity getAllAdmin(){
        List<Admin> adminList = adminRepo.findAll();
        responseDTO.setCode(VarList.RSP_SUCCESS);
        responseDTO.setMessage("Success");
        responseDTO.setContent(adminList);
        return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity updateAdmin(AdminDTO adminDTO){
        try{
            if (adminRepo.existsById(adminDTO.getAdminId())) {
                adminRepo.save(modelMapper.map(adminDTO, Admin.class));
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not A Registered Admin");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity deleteAdmin(int adminID) {
        try {
            if (adminRepo.existsById(adminID)) {
                adminRepo.deleteById(adminID);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this adminID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
