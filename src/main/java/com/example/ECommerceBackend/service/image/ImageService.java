package com.example.ECommerceBackend.service.image;


import com.example.ECommerceBackend.dto.ImageDTO;
import com.example.ECommerceBackend.exception.ResourceNotFoundException;
import com.example.ECommerceBackend.model.Image;
import com.example.ECommerceBackend.model.Product;
import com.example.ECommerceBackend.repository.ImageRepository;
import com.example.ECommerceBackend.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service

public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    @Autowired
    private IProductService productService;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository :: delete ,() -> {
            throw new ResourceNotFoundException("Image not found with id: " + id);
        });
    }

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDto = new ArrayList<>();
        for(MultipartFile file : files){
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String BuildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = BuildDownloadUrl + image.getId();
                image.setDownloadUrl(BuildDownloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(BuildDownloadUrl+savedImage.getId());
                imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageId(savedImage.getId());
                imageDTO.setImageName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDto.add(imageDTO);
            }catch(IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try{
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        }catch(IOException | SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
