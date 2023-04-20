package com.safemtech.togglerexp.repo;

import com.safemtech.togglerexp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FakeDb {

  public List<Product> getProductsRepo() {
    return List.of(
      new Product(1, "bugatti", "2021 Bugatti rogue", 3978.09),
      new Product(2, "toyota", "2020 toyota corolla", 2509.57),
      new Product(3, "audi", "2011 audi marol", 798.09),
      new Product(4, "benz", "2022 mercedez benz", 1278.09)
    );
  }

}
