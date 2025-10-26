package br.com.guntz.desafio.loja.order.receive.domain.model;

import br.com.guntz.desafio.loja.order.receive.common.IdGenerator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
public class OrderItem {

    private UUID id;

    private Product product;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalAmount;

    @Builder(builderClassName = "BrandNewOrderItemBuilder", builderMethodName = "brandNew")
    private static OrderItem createBrandNew(Product product, BigDecimal price, Integer quantity) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(price);
        Objects.requireNonNull(quantity);

        OrderItem orderItem = new OrderItem(
                IdGenerator.generateTimeBasedUUID(),
                product,
                price,
                quantity,
                BigDecimal.ZERO
        );

        orderItem.recalculateTotals();

        return orderItem;
    }

    @Builder(builderClassName = "ExistingOrderItemBuilder", builderMethodName = "existing")
    public OrderItem(UUID id, Product product, BigDecimal price, Integer quantity, BigDecimal totalAmount) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public void recalculateTotals() {
        this.setTotalAmount(this.price.multiply(new BigDecimal(this.quantity)));
    }


}
