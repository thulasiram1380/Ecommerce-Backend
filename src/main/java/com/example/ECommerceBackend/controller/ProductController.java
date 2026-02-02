package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.ProductDTO;
import com.example.ECommerceBackend.exception.ResourceNotFoundException;
import com.example.ECommerceBackend.model.Product;
import com.example.ECommerceBackend.request.AddProductRequest;
import com.example.ECommerceBackend.request.ProductUpdateRequest;
import com.example.ECommerceBackend.response.ApiResponse;
import com.example.ECommerceBackend.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // ---------------- BASIC CRUD ----------------

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> convertedProducts = productService.getConvertProducts(products);
        return ResponseEntity.ok(
                new ApiResponse("success", convertedProducts)
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductDTO productDTO = productService.convertToDto(product);
            return ResponseEntity.ok(
                    new ApiResponse("success", productDTO)
            );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        return ResponseEntity.ok(
                new ApiResponse("Product added successfully",
                        productService.addProduct(product))
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(
            @RequestBody ProductUpdateRequest request,
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                new ApiResponse("Product updated successfully",
                        productService.updateProduct(request, productId))
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(
                new ApiResponse("Product deleted successfully", productId)
        );
    }

    // ---------------- FILTER APIs ----------------

    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(
                new ApiResponse("success",
                        productService.getProductsByBrand(brand))
        );
    }

    @GetMapping("/by-name")
    public ResponseEntity<ApiResponse> getProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(
                new ApiResponse("success",
                        productService.getProductsByName(name))
        );
    }

    @GetMapping("/by-category")
    public ResponseEntity<ApiResponse> getProductsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(
                new ApiResponse("success",
                        productService.getProductsByCategory(category))
        );
    }

    @GetMapping("/by-brand-and-name")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name) {
        List<Product> products = productService.getProductsByBrandAndName(brand, name);
        List<ProductDTO> convertedProducts = productService.getConvertProducts(products);
        return ResponseEntity.ok(
                new ApiResponse("success", convertedProducts
                        )
        );
    }

    @GetMapping("/by-category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(
            @RequestParam String category,
            @RequestParam String brand) {

        return ResponseEntity.ok(
                new ApiResponse("success",
                        productService.getProductsByCategoryAndBrand(category, brand))
        );
    }

    // ---------------- COUNT API ----------------

    @GetMapping("/count/by-brand-and-name")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse("Product Count",
                        productService.countProductByBrandAndName(brand, name))
        );
    }
}
