package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.CartItemDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ResponseDTO responseDTO;

    //add product to cart using post request
    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartItemDTO cartItemDTO) {
        ResponseDTO responseDTO = cartItemService.addToCart(cartItemDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getCartItems/{empID}")
    public ResponseEntity<ResponseDTO> getCartItems(@PathVariable int empID) {
        ResponseDTO responseDTO = cartItemService.getCartItems(empID);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/removeFromCart/{empID}/{prodID}")
    public ResponseEntity<ResponseDTO> removeFromCart(@PathVariable int empID, @PathVariable int prodID) {
        ResponseDTO responseDTO = cartItemService.removeFromCart(empID, prodID);
        log.info("Test {} {} {}",empID,prodID,responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
