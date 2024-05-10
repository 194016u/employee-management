package com.example.employeemanagement.repo;

import com.example.employeemanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
}
