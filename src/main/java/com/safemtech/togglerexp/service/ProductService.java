package com.safemtech.togglerexp.service;

import com.safemtech.togglerexp.model.Product;
import com.safemtech.togglerexp.repo.FakeDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

  private static final Feature WALS_2014 = new NamedFeature("wal_2014_promo");
  private final FeatureManager featureManager;
  private FakeDb fakeDb = new FakeDb();

  public ProductService(FeatureManager featureManager) {
    this.featureManager = featureManager;
  }



  @Cacheable(key = "#productId", cacheNames = {"productCache"}, unless = "#result==null")
  public List<Product> getAllProducts(){
    if(featureManager.isActive(WALS_2014)){
      LOGGER.info("Enter getAllProductsWithDiscount method");
      return getAllProductsWithDiscount();
    }
    LOGGER.info("Enter getAllProductsWithoutDiscount method");
    return getAllProductsWithoutDiscount();
  }

  private List<Product> getAllProductsWithoutDiscount() {
    LOGGER.info("Get data from DB");
    return fakeDb.getProductsRepo();
  }

  private List<Product> getAllProductsWithDiscount() {
    List<Product> discountedProducts = new ArrayList<>();
    fakeDb.getProductsRepo().forEach(product -> {
        discountedProducts.add(new Product(product.productId(),
        product.productName(), product.productDescription(),
        (product.price() - (0.05 * product.price()))));
    });
    return discountedProducts;
  }

}
