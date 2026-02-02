package com.example.ECommerceBackend.service.product;


import com.example.ECommerceBackend.dto.ImageDTO;
import com.example.ECommerceBackend.dto.ProductDTO;
import com.example.ECommerceBackend.exception.ProductNotFoundException;
import com.example.ECommerceBackend.model.Category;
import com.example.ECommerceBackend.model.Image;
import com.example.ECommerceBackend.model.Product;
import com.example.ECommerceBackend.repository.CategoryRepository;
import com.example.ECommerceBackend.repository.ImageRepository;
import com.example.ECommerceBackend.repository.ProductRepository;
import com.example.ECommerceBackend.request.AddProductRequest;
import com.example.ECommerceBackend.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request , category));
    }

    public Product createProduct(AddProductRequest product , Category category){
        return new Product(
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getInventory(),
                product.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                ()-> {throw new ProductNotFoundException("Product not found");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest product, Long productId) {
            return productRepository.findById(productId)
                    .map(existingProduct -> updateExistingProduct(existingProduct, product))
                    .map(productRepository :: save)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());

        existingProduct.setCategory(category);
        return existingProduct;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }


    @Override
    public List<ProductDTO> getConvertProducts(List<Product> products){
        return products.stream()
                .map(this :: convertToDto)
                .toList();
    }

    @Override
    public ProductDTO convertToDto(Product product){
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDTO> imageDTOS = images.stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .toList();
        productDTO.setImages(imageDTOS);
        return productDTO;
    }


}
