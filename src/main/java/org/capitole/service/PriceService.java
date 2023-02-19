package org.capitole.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.capitole.model.dto.FilterDTO;
import org.capitole.model.dto.PriceDTO;
import org.capitole.model.entity.Price;
import org.capitole.model.repository.PriceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceService {

    private final PriceRepository priceRepository;

    public List<PriceDTO> getPrices(FilterDTO filter) {
        log.info("Getting prices using filters: {}", filter);
        return priceRepository.findAll(getSpecification(filter)).stream().map(PriceDTO::new).toList();
    }

    private Specification<Price> getSpecification(FilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.date() != null) {
                Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), filter.date());
                Predicate endDate = criteriaBuilder.lessThan(root.get("endDate"), filter.date());
                criteriaBuilder.and(startDate, endDate);
                log.debug("Adding date filter, {}", filter.date());
            }
            if (filter.brandId() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("brandId"), filter.brandId())));
                log.debug("Adding brand id filter, {}", filter.brandId());
            }
            if (filter.productId() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("productId"), filter.productId())));
                log.debug("Adding product id filter, {}", filter.productId());
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
