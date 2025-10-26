package br.com.guntz.desafio.loja.order.receive.api.controller;

import br.com.guntz.desafio.loja.order.receive.api.model.OrderInputCreated;
import br.com.guntz.desafio.loja.order.receive.api.model.OrderInputReceived;
import br.com.guntz.desafio.loja.order.receive.common.IdGenerator;
import br.com.guntz.desafio.loja.order.receive.domain.model.OrderStatus;
import br.com.guntz.desafio.loja.order.receive.domain.service.OrderProducerMessageService;
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

    private final OrderProducerMessageService orderProducerMessageService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody OrderInputCreated orderCreated) {
        log.info("Order accepted: {}", orderCreated.idExternal());

        OrderInputReceived orderReceived = convertOrderCreatedToOrderReceived(orderCreated);
        orderProducerMessageService.sendOrderToOrderProcessorService(orderReceived);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    private OrderInputReceived convertOrderCreatedToOrderReceived(OrderInputCreated orderInputCreated) {
        return new OrderInputReceived(
                IdGenerator.generateTimeBasedUUID(),
                orderInputCreated.idExternal(),
                orderInputCreated.items(),
                OrderStatus.RECEIVED
        );
    }
}
