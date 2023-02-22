package com.soa.productservice.service;

import com.soa.productservice.dto.ProductRequest;
import com.soa.productservice.dto.ProductResponse;
import com.soa.productservice.model.Product;
import com.soa.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

//    public void createProduct(ProductRequest productRequest) {
//        Product product = Product.builder()
//                .name(productRequest.getName())
//                .description(productRequest.getDescription())
//                .price(productRequest.getPrice()).build();
//
//        productRepository.save(product);
//
//        log.info("Product {} is saved", product.getId());
//    }

    public List<Product> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//
//        return products.stream().map(this::mapToProductResponse).toList();
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        ArrayList products = (ArrayList) restTemplate.getForObject(url, Object.class);

        List<LinkedHashMap<String, String>> linkedHashMaps = new ArrayList<>();


        for(int i = 0 ; i < products.size() - 1; i++){
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) products.get(i);
            linkedHashMaps.add(linkedHashMap);
        }
        List<Product> products1 = new ArrayList<>();
        for(LinkedHashMap<String, String> map : linkedHashMaps){
            products1.add(mapToProduct(map));
        }

        return products1;
    }

    private Product mapToProduct(LinkedHashMap<String, String> map) {
        Product p = new Product();

        p.setId(String.valueOf(map.get("id")));
        p.setTitle(map.get("title"));
        p.setDescription(map.get("description"));
        p.setPrice(String.valueOf(map.get("price")));
        p.setCategory(map.get("category"));
        p.setImage(map.get("image"));

        return p;
    }
}
