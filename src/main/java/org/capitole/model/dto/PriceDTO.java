package org.capitole.model.dto;

import org.capitole.model.entity.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDTO(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer pricelist,
        Integer productId,
        Integer priority,
        BigDecimal price,
        String currency) {

    public PriceDTO(Price entity) {
        this(entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPricelist(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency());
    }
}
