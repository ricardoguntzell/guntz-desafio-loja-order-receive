package br.com.guntz.desafio.loja.order.receive.domain.service;

import br.com.guntz.desafio.loja.order.receive.api.model.OrderInputReceived;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static br.com.guntz.desafio.loja.order.receive.infrastructure.rabbitmq.RabbitMQConfig.FONOUT_EXCHANGE_ORDER_RECEIVE_RECEIVED;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    public void sendOrderToOrderProcessorService(@Valid OrderInputReceived orderInputReceived) {
        orderInputReceived.updatedOrderToReceived();
        var routingKey = "";

        rabbitTemplate.convertAndSend(FONOUT_EXCHANGE_ORDER_RECEIVE_RECEIVED, routingKey, orderInputReceived);
        log.info("Order {} CREATED and inserted in exchange: {}", orderInputReceived.id(), FONOUT_EXCHANGE_ORDER_RECEIVE_RECEIVED);
    }

}
