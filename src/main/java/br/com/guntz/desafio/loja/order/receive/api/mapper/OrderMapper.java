package br.com.guntz.desafio.loja.order.receive.api.mapper;

import br.com.guntz.desafio.loja.order.receive.api.model.OrderInputCreated;
import br.com.guntz.desafio.loja.order.receive.api.model.OrderInputReceived;
import br.com.guntz.desafio.loja.order.receive.api.model.OrderItemInputCreated;
import br.com.guntz.desafio.loja.order.receive.api.model.ProductInputCreated;
import br.com.guntz.desafio.loja.order.receive.common.IdGenerator;
import br.com.guntz.desafio.loja.order.receive.domain.model.OrderItem;
import br.com.guntz.desafio.loja.order.receive.domain.model.OrderStatus;
import br.com.guntz.desafio.loja.order.receive.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderMapper {

    public OrderInputReceived convertOrderCreatedToOrderReceived(OrderInputCreated input) {
        return new OrderInputReceived(
                IdGenerator.generateTimeBasedUUID(),
                input.idExternal(),
                this.convertOrderItemCreatedToOrderItem(input.items()),
                OrderStatus.RECEIVED
        );
    }

    private Set<OrderItem> convertOrderItemCreatedToOrderItem(Set<OrderItemInputCreated> inputItems) {
        Set<OrderItem> orderItemList = new HashSet<>();

        for (OrderItemInputCreated itemInput : inputItems) {
            OrderItem orderItem = OrderItem.existing()
                    .id(itemInput.id())
                    .product(this.convertProductCreatedToProduct(itemInput.product()))
                    .price(itemInput.price())
                    .quantity(itemInput.quantity())
                    .build();
            orderItemList.add(orderItem);
        }

        return orderItemList;
    }

    private Product convertProductCreatedToProduct(ProductInputCreated input) {
        return Product.existing()
                .id(input.id())
                .name(input.name())
                .price(input.price())
                .build();
    }

}
