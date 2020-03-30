package pl.haladyj.tests.service;

import pl.haladyj.tests.service.dto.ProductCounterDto;
import pl.haladyj.tests.service.dto.ProductDto;

import java.util.Optional;

public interface ProductService {

    Optional<ProductDto> findProductById(Long id);
    Optional<ProductCounterDto> findProductCountsById(Long id);

}
