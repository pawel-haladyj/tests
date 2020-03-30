package pl.haladyj.tests.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.haladyj.tests.service.ProductServiceImpl;
import pl.haladyj.tests.service.dto.ProductDto;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductResource {

    private final ProductServiceImpl service;

    public ProductResource(ProductServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        Optional<ProductDto> productDto = service.findProductById(id);
        return productDto
                .map(responce -> (ResponseEntity.ok().body(responce)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
