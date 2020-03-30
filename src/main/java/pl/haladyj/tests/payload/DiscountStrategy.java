package pl.haladyj.tests.payload;

import org.springframework.stereotype.Component;
import pl.haladyj.tests.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

@Component
public class DiscountStrategy {

    private static final BigDecimal DISCOUNT_LEVEL_0 = BigDecimal.valueOf(0L);
    private static final BigDecimal DISCOUNT_LEVEL_1 = BigDecimal.valueOf(3L);
    private static final BigDecimal DISCOUNT_LEVEL_2 = BigDecimal.valueOf(5L);

    private static final BigDecimal VALUE_LEVEL_1 = BigDecimal.valueOf(500L);
    private static final BigDecimal VALUE_LEVEL_2 = BigDecimal.valueOf(2000L);

    public BigDecimal calculateDiscountedPrice(Product product) {

        checkArgument(nonNull(product), "expected non null product");

        return product.getPrice().multiply(
                BigDecimal.valueOf(100).subtract(pickDiscount(product)))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal pickDiscount(Product product) {
        if(product.getPrice().compareTo(VALUE_LEVEL_1)>=0
         && product.getPrice().compareTo(VALUE_LEVEL_2)<0){
            return DISCOUNT_LEVEL_1;
        }
        if (product.getPrice().compareTo(VALUE_LEVEL_2) >= 0) {
            return DISCOUNT_LEVEL_2;
        }
        return DISCOUNT_LEVEL_0;
    }
}
