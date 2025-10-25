package br.com.guntz.desafio.loja.order.receive.api.model;

import br.com.guntz.desafio.loja.order.receive.domain.model.OrderItem;
import br.com.guntz.desafio.loja.order.receive.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public record OrderInputReceived(

        @NotNull
        UUID id,

        @NotNull
        UUID idExternal,

        @NotNull
        Set<OrderItem> items,

        @NotNull
        BigDecimal totalAmount,

        @NotNull
        Integer totalItems,

        @NotNull
        OrderStatus orderStatus,

        @NotNull
        OffsetDateTime createdAt

) {

    public void updatedOrderToReceived() {
        new OrderInputReceived(this.id,
                this.idExternal,
                this.items,
                this.totalAmount(),
                this.totalItems,
                OrderStatus.RECEIVED,
                this.createdAt
        );
    }

}
