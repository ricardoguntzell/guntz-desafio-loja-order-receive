package br.com.guntz.desafio.loja.order.receive.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void given_NewOrderItem_whenAddValidOrderItem_shouldNotGenerateException() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> OrderItem.brandNew()
                        .product(ProductTestDataBuilder.aProductPS5())
                        .price(ProductTestDataBuilder.aProductPS5().getPrice())
                        .quantity(1)
                        .build());
    }

    @Test
    void given_NewOrderItem_whenAddInvalidOrderItem_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> OrderItem.brandNew()
                        .product(null)
                        .quantity(null)
                        .build());
    }
}