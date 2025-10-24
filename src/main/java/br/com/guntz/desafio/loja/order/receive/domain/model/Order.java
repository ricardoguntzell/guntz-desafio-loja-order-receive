package br.com.guntz.desafio.loja.order.receive.domain.model;

import br.com.guntz.desafio.loja.order.receive.common.IdGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Order {

    private UUID id;
    private UUID idExternal;

    private Set<OrderItem> items;

    private BigDecimal totalAmount;
    private Integer totalItems;

    private OrderStatus orderStatus;
    private OffsetDateTime createdAt;

    @Builder(builderClassName = "BrandNewOrderBuilder", builderMethodName = "brandNew")
    private static Order createBrandNew() {

        Order order = new Order(
                IdGenerator.generateTimeBasedUUID(),
                IdGenerator.generateTimeBasedUUID(),
                new HashSet<>(),
                BigDecimal.ZERO,
                0,
                OrderStatus.CREATED,
                OffsetDateTime.now()
        );

        order.recalculateTotals();

        return order;
    }

    @Builder(builderClassName = "ExistingOrderBuilder", builderMethodName = "existing")
    public Order(UUID id, UUID idExternal, Set<OrderItem> items, BigDecimal totalAmount, Integer totalItems,
                 OrderStatus orderStatus, OffsetDateTime createdAt) {
        this.id = id;
        this.idExternal = idExternal;
        this.items = items;
        this.totalAmount = totalAmount;
        this.totalItems = totalItems;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public void addItem(Product product, Integer quantity) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(quantity);

        OrderItem item = OrderItem.brandNew()
                .product(product)
                .price(product.getPrice())
                .quantity(quantity)
                .build();
        item.recalculateTotals();

        this.items.add(item);
        this.recalculateTotals();
    }

    private void recalculateTotals() {
        BigDecimal totalItemsAmount = this.items.stream()
                .map(OrderItem::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer totalItemsQuantity = this.items.stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);

        this.setTotalAmount(totalItemsAmount);
        this.setTotalItems(totalItemsQuantity);
    }

    public Set<OrderItem> getItems() {
        return Collections.unmodifiableSet(this.items);
    }
}
