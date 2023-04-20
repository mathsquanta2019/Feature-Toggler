package com.safemtech.togglerexp.controller;

import com.safemtech.togglerexp.model.Product;
import com.safemtech.togglerexp.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
  private final ProductService productService;
  public ProductController(ProductService productService){
    this.productService = productService;
  }

  @GetMapping("/cars")
  public List<Product> getAllProducts(){
    return productService.getAllProducts();
  }
}
