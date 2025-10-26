package br.com.guntz.desafio.loja.order.receive.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemInputCreated(
        @NotNull
        UUID id,

        @Valid
        @NotNull
        ProductInputCreated product,

        @NotNull
        BigDecimal price,

        @NotNull
        Integer quantity
) {

}
