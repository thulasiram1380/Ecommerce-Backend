package com.example.ECommerceBackend.service.image;

import com.example.ECommerceBackend.dto.ImageDTO;
import com.example.ECommerceBackend.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImages(List<MultipartFile> files , Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
