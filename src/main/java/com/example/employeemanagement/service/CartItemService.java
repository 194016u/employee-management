package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.CartItemDTO;
import com.example.employeemanagement.dto.ResponseDTO;
import com.example.employeemanagement.entity.CartItem;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Product;
import com.example.employeemanagement.repo.CartItemRepo;
import com.example.employeemanagement.repo.EmployeeRepo;
import com.example.employeemanagement.repo.ProductRepo;
import com.example.employeemanagement.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseDTO addToCart(CartItemDTO cartItemDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Employee employee = employeeRepo.findById(cartItemDTO.getEmpId()).orElse(null);
            Product product = productRepo.findById(cartItemDTO.getProdId()).orElse(null);
            if (employee != null && product != null) {
                CartItem cartItem = new CartItem();
                cartItem.setEmployee(employee);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartItemDTO.getQuantity());
                cartItemRepo.save(cartItem);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Item added to cart successfully");
                responseDTO.setContent(cartItemDTO);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Employee or Product not found");
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }
    public ResponseDTO getCartItems(int empID) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<CartItem> cartItems = cartItemRepo.findByEmployee_EmpID(empID);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(cartItems);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }
    public ResponseDTO removeFromCart(int empID, int prodID) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            cartItemRepo.deleteByEmployee_EmpIDAndProduct_ProdID(empID, prodID);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Item removed from cart successfully");
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }


}
