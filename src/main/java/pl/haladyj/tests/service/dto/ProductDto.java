package pl.haladyj.tests.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.haladyj.tests.model.ProductType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private ProductType type;
    private BigDecimal initialPrice;
    private BigDecimal discountedPrice;
    private Long clickCounter;
}
