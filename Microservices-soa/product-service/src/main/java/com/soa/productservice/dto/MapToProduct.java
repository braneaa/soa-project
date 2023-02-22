package com.soa.productservice.dto;

import com.soa.productservice.model.Product;

import java.util.LinkedHashMap;

public class MapToProduct {

    public Product mapToProduct(LinkedHashMap<String, String> map){
        Product product = new Product();

        return product;
    }

}
