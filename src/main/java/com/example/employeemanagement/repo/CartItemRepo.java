package com.example.employeemanagement.repo;

import com.example.employeemanagement.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByEmployee_EmpID(int empID);
}
