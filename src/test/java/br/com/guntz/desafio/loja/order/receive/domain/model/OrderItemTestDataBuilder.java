package br.com.guntz.desafio.loja.order.receive.domain.model;

public class OrderItemTestDataBuilder {

    private OrderItemTestDataBuilder() {
    }

    public static OrderItem aOrderItemPS5() {
        return OrderItem.brandNew()
                .product(ProductTestDataBuilder.aProductPS5())
                .price(ProductTestDataBuilder.aProductPS5().getPrice())
                .quantity(1)
                .build();
    }

    public static OrderItem aOrderItemNoteASUS() {
        return OrderItem.brandNew()
                .product(ProductTestDataBuilder.aProductNoteASUS())
                .price(ProductTestDataBuilder.aProductNoteASUS().getPrice())
                .quantity(1)
                .build();
    }


}
