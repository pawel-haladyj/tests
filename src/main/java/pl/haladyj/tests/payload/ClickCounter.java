package pl.haladyj.tests.payload;

import org.springframework.stereotype.Component;
import pl.haladyj.tests.model.Product;

@Component
public class ClickCounter {
    public Long updateCounter(Product product) {

        Long currentClickCounter = product.getClickCounter();
        Long updatedClickCounter = currentClickCounter + 1;
        product.setClickCounter(updatedClickCounter);

        return updatedClickCounter;
    }
}

