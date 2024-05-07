package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.ProductDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getAllProduct")
    public ResponseEntity getAllProducts(){
        return productService.getAllProduct();
    }

    @PostMapping(value = "/saveProduct")
    public ResponseEntity saveProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity(productService.saveProduct(productDTO), HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/updateProduct")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity(productService.updateProduct(productDTO),HttpStatus.ACCEPTED);
    }


}
