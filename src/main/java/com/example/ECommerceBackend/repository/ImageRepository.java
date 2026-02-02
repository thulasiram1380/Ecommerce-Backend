package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {


    List<Image> findByProductId(Long id);
}
