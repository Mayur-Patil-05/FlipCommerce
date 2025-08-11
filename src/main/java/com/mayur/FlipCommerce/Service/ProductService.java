package com.mayur.FlipCommerce.Service;

import com.mayur.FlipCommerce.DTOs.ProductDto;
import com.mayur.FlipCommerce.Exceptions.ProductNotFoundException;
import com.mayur.FlipCommerce.Model.Product;
import com.mayur.FlipCommerce.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public String addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return "Product added successfully";
    }

    public Page<ProductDto> getAllProducts(int page, int size, String direction, String sortBy) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> modelMapper.map(product, ProductDto.class));
    }

    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + productId));
        return modelMapper.map(product, ProductDto.class);
    }

    @Transactional
    public String updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + productId));

        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getDiscountPrice() != null) {
            product.setDiscountPrice(productDto.getDiscountPrice());
        }
        if (productDto.getStockQuantity() != null) {
            product.setStockQuantity(productDto.getStockQuantity());
        }
        return "Product updated successfully";
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with id : " + productId);
        }
        productRepository.deleteById(productId);
    }
}
