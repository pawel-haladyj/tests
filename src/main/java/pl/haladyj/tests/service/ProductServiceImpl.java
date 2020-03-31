package pl.haladyj.tests.service;

import org.springframework.stereotype.Service;
import pl.haladyj.tests.model.converter.ProductConverter;
import pl.haladyj.tests.payload.ClickCounter;
import pl.haladyj.tests.payload.DiscountStrategy;
import pl.haladyj.tests.repository.ProductRepository;
import pl.haladyj.tests.service.dto.ProductCounterDto;
import pl.haladyj.tests.service.dto.ProductDto;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository repository;
    private final ProductConverter converter;
    private final DiscountStrategy strategy;
    private final ClickCounter counter;

    public ProductServiceImpl(ProductRepository repository,
                              ProductConverter converter,
                              DiscountStrategy strategy,
                              ClickCounter counter) {
        this.repository = repository;
        this.converter = converter;
        this.strategy = strategy;
        this.counter = counter;
    }


    @Override
    public Optional<ProductDto> findProductById(Long id) {

        checkArgument(nonNull(id), "Expected non-null id");

        return repository
                .findById(id)
                .map(product -> {
                    product.setClickCounter(counter.updateCounter(product));
                    product = repository.save(product);
                    ProductDto productDto = converter.toDto(product);
                    productDto.setDiscountedPrice(strategy.calculateDiscountedPrice(product));
                    return productDto;
                });

    }

    @Override
    public Optional<ProductCounterDto> findProductCountsById(Long id) {

        checkArgument(nonNull(id), "Expected non null id");
        return repository
                .findById(id)
                .map(converter::toCounterDto);
    }
}
