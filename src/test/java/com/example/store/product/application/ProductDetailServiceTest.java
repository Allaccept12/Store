package com.example.store.product.application;

import com.example.store.common.model.Money;
import com.example.store.product.domain.Product;
import com.example.store.product.domain.ProductId;
import com.example.store.product.domain.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@MockitoSettings(strictness = Strictness.WARN)
class ProductDetailServiceTest {

    @InjectMocks
    ProductDetailService productDetailService;

    @Mock
    ProductRepository productRepository;


    @Test
    void getProductDetail() {
        Product product = Product.createProduct(ProductId.of("product_1"), Money.of(100), "testProduct");
        given(productRepository.findById(any()))
        .willReturn(Optional.of(product));

        ProductDetailRes result = productDetailService.getProductDetail("product_1");
        Assertions.assertEquals(product.getName(), result.getName());


    }
}