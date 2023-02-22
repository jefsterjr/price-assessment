package org.capitole.service;

import org.capitole.model.dto.FilterDTO;
import org.capitole.model.dto.PriceDTO;
import org.capitole.model.entity.Price;
import org.capitole.model.repository.PriceRepository;
import org.capitole.util.exception.PriceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class PriceServiceUnitTest {

    @Mock
    private PriceRepository repository;


    private PriceService service;

    private Price price;

    @BeforeEach
    void setUp() {
        service = new PriceService(repository);
        createPrice();
    }

    @Test
    void getPricesWithOnlyBrandId() {
        mockFindAll();
        PriceDTO priceDTO = service.getPrices(new FilterDTO(null, 1, null));
        Assertions.assertEquals(new PriceDTO(price), priceDTO);
    }

    @Test
    void getPricesWithOnlyProductId() {
        mockFindAll();
        PriceDTO priceDTO = service.getPrices(new FilterDTO(1, null, null));
        Assertions.assertEquals(new PriceDTO(price), priceDTO);
    }

    @Test
    void getPricesWithOnlyDate() {
        mockFindAll();
        PriceDTO priceDTO = service.getPrices(new FilterDTO(null, null, LocalDateTime.parse("2020-06-10T00:00:00")));
        Assertions.assertEquals(new PriceDTO(price), priceDTO);
    }

    @Test
    void getPricesWithNoParameter() {
        mockFindAll();
        PriceDTO priceDTO = service.getPrices(new FilterDTO(null, null, null));
        Assertions.assertEquals(new PriceDTO(price), priceDTO);
    }

    @Test
    void getPricesWithNoResult() {
        Mockito.when(repository.findAll(ArgumentMatchers.any(Specification.class), ArgumentMatchers.any(Pageable.class))).thenReturn(Page.empty());
        Assertions.assertThrows(PriceNotFoundException.class, () -> {
            service.getPrices(new FilterDTO(null, null, null));
        });
    }

    private void createPrice() {
        price = new Price(1L, 1, LocalDateTime.parse("2020-06-10T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME), 1, 1, 1, BigDecimal.valueOf(35), "EUR");
    }

    private void mockFindAll() {
        Mockito.when(repository.findAll(ArgumentMatchers.any(Specification.class), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(price)));
    }
}
