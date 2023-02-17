package org.capitole.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.capitole.model.dto.FilterDTO;
import org.capitole.model.dto.PriceDTO;
import org.capitole.model.entity.Price;
import org.capitole.service.PriceService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Validated
@RequiredArgsConstructor
public class PricesController {

    private final PriceService priceService;

    @GetMapping(path = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get prices by filters")
    public List<PriceDTO> productPrice(FilterDTO filter) {
        return priceService.getPrices(filter);
    }
}
