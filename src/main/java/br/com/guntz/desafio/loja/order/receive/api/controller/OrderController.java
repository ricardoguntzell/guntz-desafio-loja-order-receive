package br.com.guntz.desafio.loja.order.receive.api.controller;

import br.com.guntz.desafio.loja.order.receive.api.model.OrderInputReceived;
import br.com.guntz.desafio.loja.order.receive.domain.service.OrderMessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderMessagingService orderMessagingService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody OrderInputReceived orderInputReceived) {
        log.info("Order accepted: {}", orderInputReceived.id());

        orderMessagingService.sendOrderToOrderProcessorService(orderInputReceived);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
