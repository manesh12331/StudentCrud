package com.stud.StudentCrud.controller;

import com.stud.StudentCrud.entity.Product;
import com.stud.StudentCrud.exception.ResourceNotFoundException;
import com.stud.StudentCrud.repository.ProductReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductReository productReository;

    @PostMapping("/addEmployee")
    public Product createEmployee(@Validated @RequestBody Product product) {
        return productReository.save(product);
    }

    @GetMapping("/employees")
    public List<Product> getAllProducts(){
        System.out.println("master branch");
        return productReository.findAll();

    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<Product> getEmployeeById(@PathVariable Long id){
        Product product=productReository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee Not exist with id"+id));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Product> updateEmployee(@PathVariable Long id,@RequestBody Product employeeDetails){
        Product product=productReository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee Not exist with id"+id));

        System.out.println("in spring");
        product.setFirstName(employeeDetails.getFirstName());
        product.setLastName(employeeDetails.getLastName());
        product.setEmailId(employeeDetails.getEmailId());

        Product updateEmployee=productReository.save(product);
        return ResponseEntity.ok(updateEmployee);
    }
    @DeleteMapping("/employees/{id}")
    public  ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Product employee=productReository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee Not exist with id"+id));
        productReository.delete(employee);
        Map<String,Boolean> response= new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


}



