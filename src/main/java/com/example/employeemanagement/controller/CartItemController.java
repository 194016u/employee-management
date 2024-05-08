package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ResponseDTO responseDTO;

}
