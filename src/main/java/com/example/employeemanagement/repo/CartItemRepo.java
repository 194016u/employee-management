package com.example.employeemanagement.repo;

import com.example.employeemanagement.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByEmployee_EmpID(int empID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CartItem c WHERE c.employee.empID = ?1 AND c.product.prodID = ?2")
    void deleteByEmployee_EmpIDAndProduct_ProdID(int empID, int prodID);
}
