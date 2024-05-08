package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.CartItemDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartItemDTO cartItemDTO) {
        ResponseDTO responseDTO = cartItemService.addToCart(cartItemDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

//    @GetMapping("/getCartItems/{empID}")
//    public ResponseEntity<ResponseDTO> getCartItems(@PathVariable int empID) {
//        ResponseDTO responseDTO = cartItemService.getCartItems(empID);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/removeFromCart/{empID}/{prodID}")
//    public ResponseEntity<ResponseDTO> removeFromCart(@PathVariable int empID, @PathVariable int prodID) {
//        ResponseDTO responseDTO = cartItemService.removeFromCart(empID, prodID);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }

}
