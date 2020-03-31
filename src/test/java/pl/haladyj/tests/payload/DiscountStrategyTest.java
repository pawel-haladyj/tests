package pl.haladyj.tests.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.haladyj.tests.model.Product;
import pl.haladyj.tests.model.ProductType;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountStrategyTest {

    private DiscountStrategy discountStrategy;

    @BeforeEach()
    void setUp() {
        discountStrategy = new DiscountStrategy();
    }

    @Test
    void shouldThrowExceptionWhenProductIsNull() {
        //given
        Product givenNullProduct = null;
        String expectedExceptionMessage = "expected non null product";

        //when
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> discountStrategy.calculateDiscountedPrice(givenNullProduct)
        );
        //then
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @DisplayName("Should calculate discounted price: ")
    @ParameterizedTest(name = "{index}-{0}")
    @MethodSource("provideProduct")
    void shouldCalculateDiscountedPrice(
            //given
            String displayNameTest, Product givenProduct, BigDecimal expectedDiscountedPrice
    ) {
        //when
        BigDecimal actualDiscountedPrice = discountStrategy.calculateDiscountedPrice(givenProduct);

        //then
        assertEquals(expectedDiscountedPrice, actualDiscountedPrice);
    }

    static Stream<Arguments> provideProduct() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("name1");
        product1.setDescription("description1");
        product1.setProductType(ProductType.MALE);
        product1.setPrice(BigDecimal.valueOf(300L));
        product1.setClickCounter(1L);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("name2");
        product2.setDescription("description2");
        product2.setProductType(ProductType.FEMALE);
        product2.setPrice(BigDecimal.valueOf(800L));
        product2.setClickCounter(1L);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("name3");
        product3.setDescription("description3");
        product3.setProductType(ProductType.KID);
        product3.setPrice(BigDecimal.valueOf(2000L));
        product3.setClickCounter(1L);

        return Stream.of(
                Arguments.of("strategy 0-499", product1, BigDecimal.valueOf(30000, 2)),
                Arguments.of("strategy 500-1999", product2, BigDecimal.valueOf(77600, 2)),
                Arguments.of("strategy up 2000", product3, BigDecimal.valueOf(190000, 2))
        );
    }
}
