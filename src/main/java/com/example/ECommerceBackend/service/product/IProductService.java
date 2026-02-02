package com.example.ECommerceBackend.service.product;

import com.example.ECommerceBackend.dto.ProductDTO;
import com.example.ECommerceBackend.model.Product;
import com.example.ECommerceBackend.request.AddProductRequest;
import com.example.ECommerceBackend.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product , Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category , String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category , String name);
    Long countProductByBrandAndName(String brand , String name);



    List<ProductDTO> getConvertProducts(List<Product> products);

    ProductDTO convertToDto(Product product);


}
