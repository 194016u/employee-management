package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int prodID;
    private String prodName;
    private String prodCategory;
    private Date manufacDate;
    private Date expDate;
}
