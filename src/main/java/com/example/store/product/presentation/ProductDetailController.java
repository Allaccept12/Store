package com.example.store.product.presentation;


import com.example.store.product.application.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable String productId) {
        return ResponseEntity.ok(productDetailService.getProductDetail(productId));
    }
}
