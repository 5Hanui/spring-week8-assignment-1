package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.domain.ProductRepository;
import com.codesoom.assignment.dto.ProductData;
import com.codesoom.assignment.errors.ProductNotFoundException;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 상품을 관리합니다.
 */
@Service
@Transactional
public class ProductService {
    private final Mapper mapper;
    private final ProductRepository productRepository;

    public ProductService(
            Mapper dozerMapper,
            ProductRepository productRepository
    ) {
        this.mapper = dozerMapper;
        this.productRepository = productRepository;
    }

    /**
     * 전체 상품 목록을 반환합니다.
     *
     * @return 상품 목록
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * 상품 하나의 정보를 반환합니다.
     *
     * @param id 상품 식별자
     * @return 상품
     */
    public Product getProduct(Long id) {
        return findProduct(id);
    }

    /**
     * 상품의 정보로 상품을 생성하고 반환합니다.
     *
     * @param productData 생성할 상품의 정보
     * @return 생성된 상품
     */
    public Product createProduct(ProductData productData) {
        Product product = mapper.map(productData, Product.class);
        return productRepository.save(product);
    }

    /**
     * 상품의 정보를 수정하고 반환합니다.
     *
     * @param id 상품 식별자
     * @param productData 상품의 정보
     * @return 수정된 상품
     */
    public Product updateProduct(Long id, ProductData productData) {
        Product product = findProduct(id);

        product.changeWith(mapper.map(productData, Product.class));

        return product;
    }

    /**
     * 상품을 삭제합니다.
     *
     * @param id 상품 식별자
     * @return 삭제된 상품
     */
    public Product deleteProduct(Long id) {
        Product product = findProduct(id);

        productRepository.delete(product);

        return product;
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
