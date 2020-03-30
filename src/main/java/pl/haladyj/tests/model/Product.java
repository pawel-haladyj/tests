package pl.haladyj.tests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name="description", nullable = false)
    private String description;

    @NotNull
    @Column(name="product_type", nullable = false)
    private ProductType productType;

    @NotNull
    @Column(name="price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name="click_counter", nullable = false)
    private Long clickCounter;
}
