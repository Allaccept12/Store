package com.example.store.product.application;


import com.example.store.order.application.NotFoundProductException;
import com.example.store.product.domain.Product;
import com.example.store.product.domain.ProductId;
import com.example.store.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductDetailService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDetailRes getProductDetail(String productId) {
        Product product = productRepository.findById(ProductId.of(productId))
                .orElseThrow(NotFoundProductException::new);
        return new ProductDetailRes(product.getPrice().getValue(),product.getName());
    }
}
