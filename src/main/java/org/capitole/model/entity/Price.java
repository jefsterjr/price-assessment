package org.capitole.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Price {

    @Id
    private Long id;
    @Column(name = "BRAND_ID")
    private Integer brandId;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private Integer pricelist;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column
    private Integer priority;
    @Column(name = "PRICE")
    private BigDecimal price;

    @Column
    private String currency;
}
