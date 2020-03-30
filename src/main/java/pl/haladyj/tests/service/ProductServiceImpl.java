package pl.haladyj.tests.service;

import org.springframework.stereotype.Service;
import pl.haladyj.tests.model.converter.ProductConverter;
import pl.haladyj.tests.payload.DiscountStrategy;
import pl.haladyj.tests.repository.ProductRepository;
import pl.haladyj.tests.service.dto.ProductCounterDto;
import pl.haladyj.tests.service.dto.ProductDto;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository repository;
    private final ProductConverter converter;
    private final DiscountStrategy strategy;

    public ProductServiceImpl(ProductRepository repository, ProductConverter converter, DiscountStrategy strategy) {
        this.repository = repository;
        this.converter = converter;
        this.strategy = strategy;
    }


    @Override
    public Optional<ProductDto> findProductById(Long id) {
        return repository
                .findProductById(id)
                .map(product -> {
                    ProductDto productDto = converter.toDto(product);
                    productDto.setDiscountedPrice(strategy.calculateDiscountedPrice(product));
                    return productDto;
                });

    }

    @Override
    public Optional<ProductCounterDto> findProductCountsById(Long id) {
        return Optional.empty();
    }
}
