package com.stud.StudentCrud.repository;

import com.stud.StudentCrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReository extends JpaRepository<Product, Long> {
}
