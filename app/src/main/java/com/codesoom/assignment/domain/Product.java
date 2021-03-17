package com.codesoom.assignment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 상품 정보.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private String name;

    @Setter
    private String maker;

    @Setter
    private Integer price;

    @Setter
    private String image;

    @Builder
    public Product(String name, String maker, Integer price, String image) {
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.image = image;
    }

    /**
     * 상품 정보를 수정합니다.
     *
     * @param source 수정할 상품 정보
     */
    public void updateWith(Product source) {
        this.name = source.name;
        this.maker = source.maker;
        this.price = source.price;
        this.image = source.image;
    }
}
