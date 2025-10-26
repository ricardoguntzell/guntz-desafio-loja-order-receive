# Microsserviço: Order Receive

**Repositório:** [guntz-desafio-loja-order-receive](https://github.com/ricardoguntzell/guntz-desafio-loja-order-receive)

Este é o microsserviço responsável por receber os pedidos da loja. Ele atua como um gateway, expondo uma API REST para a criação de novos pedidos.

## Funcionalidades

- **API REST para Recebimento de Pedidos**: Expõe um endpoint `POST /orders` para receber novos pedidos.
- **Validação de Entrada**: Valida a estrutura e os dados básicos do pedido recebido.
- **Comunicação Assíncrona**: Publica os pedidos válidos em uma fila do RabbitMQ para serem processados por outro serviço (`order-processor`).
- **Alta Disponibilidade**: Por ser leve e desacoplado do processamento pesado, este serviço consegue lidar com um alto volume de requisições e responder rapidamente.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring for RabbitMQ
- Lombok
- SpringDoc OpenAPI (Swagger)

## Como Executar

Certifique-se de que o RabbitMQ esteja em execução e as variáveis de ambiente (`RABBITMQ_DEFAULT_USER` e `RABBITMQ_DEFAULT_PASS`) estejam configuradas.

```bash
# A partir da raiz do projeto (guntz-desafio-loja)
cd microservices/order-receive
./mvnw spring-boot:run
```

O serviço estará disponível na porta `8080`.

## Documentação da API e Observabilidade

### Documentação (Swagger)

A documentação interativa da API, gerada com SpringDoc/Swagger, está disponível em:

**URL**: http://localhost:8080/swagger-ui.html

### Observabilidade (Prometheus)

O serviço expõe métricas para o Prometheus no endpoint `/actuator/prometheus`. Essas métricas incluem informações sobre a JVM, requisições HTTP, e outros dados vitais para monitoramento.

## Exemplo de Requisição

**POST** `http://localhost:8080/orders`

**Body (raw/json):**
```json
{
    "idExternal": "e0e9154c-33ba-4e08-9562-e536dc1ce34a",
    "items": [
        {
            "id": "019a13b6-deff-7631-bd85-6f90cfd97c83",
            "product": {
                "id": "019a13b6-defe-78a7-a357-06a7baf695cb",
                "name": "Notebook ASUS Express",
                "price": 9500
            },
            "price": 9500,
            "quantity": 2
        },
        {
            "id": "019a13b6-deff-754c-873f-b1573be62852",
            "product": {
                "id": "019a13b6-defe-74e9-bb60-de1c7868625c",
                "name": "PS5 Slim",
                "price": 4000
            },
            "price": 4000,
            "quantity": 2
        }
    ],
    "orderStatus": "CREATED"
}
```