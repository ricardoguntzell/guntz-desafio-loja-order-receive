package br.com.guntz.desafio.loja.order.receive.domain.model;

import br.com.guntz.desafio.loja.order.receive.common.IdGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Product {

    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @Builder(builderClassName = "BrandNewProductBuilder", builderMethodName = "brandNew")
    private static Product createBrandNew(String name, BigDecimal price) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);

        return new Product(
                IdGenerator.generateTimeBasedUUID(),
                name,
                price
        );
    }

    @Builder(builderClassName = "ExistingProductBuilder", builderMethodName = "existing")
    private Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
