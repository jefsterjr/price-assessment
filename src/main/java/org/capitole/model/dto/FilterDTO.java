package org.capitole.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record FilterDTO(
        Integer productId,
        Integer brandId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime date) {
}
