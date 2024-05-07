package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.ProductDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Product;
import com.example.employeemanagement.repo.ProductRepo;
import com.example.employeemanagement.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity getAllProduct(){
        List<Product> productList=productRepo.findAll();
        responseDTO.setCode(VarList.RSP_SUCCESS);
        responseDTO.setMessage("Success");
        responseDTO.setContent(productList);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }
    public ResponseEntity saveProduct(ProductDTO productDTO){
        try{
            if (productRepo.existsById(productDTO.getProdID())) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Product Registered");
                responseDTO.setContent(productDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                productRepo.save(modelMapper.map(productDTO, Product.class));
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(productDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity updateProduct(ProductDTO productDTO){
        try{
            if (productRepo.existsById(productDTO.getProdID())) {
                productRepo.save(modelMapper.map(productDTO, Product.class));
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(productDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not A Registered Product");
                responseDTO.setContent(productDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    public ResponseEntity searchProduct(int productID){
        if (productRepo.existsById(productID)){
            Product product =productRepo.findById(productID).orElse(null);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(product);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }else {
            return null;
        }
    }
}
