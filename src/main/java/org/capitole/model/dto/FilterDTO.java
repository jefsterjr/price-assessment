package org.capitole.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record FilterDTO(

        @Min(value = 1, message = "Product identification cannot be less than 1")
        Integer productId,
        @Min(value = 1, message = "Brand identification cannot be less than 1")
        Integer brandId,

        @PastOrPresent(message = "Date should be on past or the current date")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date) {
}
