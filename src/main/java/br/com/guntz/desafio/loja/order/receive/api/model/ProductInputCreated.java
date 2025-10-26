package br.com.guntz.desafio.loja.order.receive.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductInputCreated(
        @NotNull
        UUID id,

        @Valid
        @NotNull
        String name,

        @NotNull
        BigDecimal price
) {

}
