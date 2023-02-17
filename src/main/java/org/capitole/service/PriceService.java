package org.capitole.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
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
public class PriceService {

    private final PriceRepository priceRepository;


    public List<PriceDTO> getPrices(FilterDTO filter) {
        return priceRepository.findAll(getSpecification(filter)).stream().map(PriceDTO::new).toList();
    }

    private Specification<Price> getSpecification(FilterDTO filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), filter.getDate()));
                predicates.add(criteriaBuilder.lessThan(root.get("endDate"), filter.getDate()));
            }
            if (filter.getBrandId() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("brandId"), filter.getBrandId())));
            }
            if (filter.getProductId() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("productId"), filter.getProductId())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
