package org.capitole.controller;

import org.capitole.model.dto.FilterDTO;
import org.capitole.model.dto.PriceDTO;
import org.capitole.service.PriceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PriceControllerUnitTest {

    @Mock
    private PriceService service;

    private PriceController controller;

    @BeforeEach
    void setUp() {
        controller = new PriceController(service);
    }

    @Test
    void getPricesWithAllParameters() {
        Mockito.when(service.getPrices(ArgumentMatchers.any(FilterDTO.class)))
                .thenReturn(Collections.singletonList(new PriceDTO(1, LocalDateTime.parse("2020-06-10T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME), 1, 1, 1, BigDecimal.valueOf(35), "EUR")));
        List<PriceDTO> resultList = controller.productPrice(new FilterDTO(1, 1, LocalDateTime.parse("2020-06-14T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        Assertions.assertEquals(resultList.size(), 1);
    }

    @Test
    void getPricesWithNoParameters() {
        Mockito.when(service.getPrices(ArgumentMatchers.any(FilterDTO.class)))
                .thenReturn(Collections.singletonList(new PriceDTO(1, LocalDateTime.parse("2020-06-10T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME), 1, 1, 1, BigDecimal.valueOf(35), "EUR")));
        List<PriceDTO> resultList = controller.productPrice(new FilterDTO(null, null, null));
        Assertions.assertEquals(resultList.size(), 1);
    }

    @Test
    void getPricesWithEmptyReturn() {
        Mockito.when(service.getPrices(ArgumentMatchers.any(FilterDTO.class)))
                .thenReturn(Collections.emptyList());
        List<PriceDTO> resultList = controller.productPrice(new FilterDTO(1, 1, LocalDateTime.parse("2020-06-14T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        Assertions.assertEquals(resultList.size(), 0);
    }
}
