package pl.haladyj.tests.model.converter;

import org.springframework.stereotype.Component;
import pl.haladyj.tests.model.Product;
import pl.haladyj.tests.service.dto.ProductCounterDto;
import pl.haladyj.tests.service.dto.ProductDto;

@Component
public class ProductConverter implements Converter<Product, ProductDto> {
    @Override
    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getInitialPrice());
        product.setProductType(productDto.getType());
        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setInitialPrice(product.getPrice());
        productDto.setType(product.getProductType());
        productDto.setClickCounter(product.getClickCounter());
        return productDto;
    }

    public ProductCounterDto toCounterDto(Product product) {

        ProductCounterDto productCounterDto = new ProductCounterDto();
        productCounterDto.setClickCounter(product.getClickCounter());

        return productCounterDto;
    }
}
