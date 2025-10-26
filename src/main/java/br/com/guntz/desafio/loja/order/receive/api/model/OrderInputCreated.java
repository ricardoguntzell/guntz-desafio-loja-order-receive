package br.com.guntz.desafio.loja.order.receive.api.model;

import br.com.guntz.desafio.loja.order.receive.domain.model.OrderItem;
import br.com.guntz.desafio.loja.order.receive.domain.model.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record OrderInputCreated(
        @NotNull
        UUID idExternal,

        @Valid
        @NotNull
        Set<OrderItem> items,

        @NotNull
        OrderStatus orderStatus
) {

}
