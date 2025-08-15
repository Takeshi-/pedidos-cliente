# Pedido Microservice

## Descrição
Microserviço em Java 21 com Spring Boot que consome mensagens de uma fila RabbitMQ, persiste em banco MySQL e fornece uma API REST para:
- Valor total do pedido
- Quantidade de pedidos por cliente
- Lista de pedidos realizados por cliente

## Tecnologias
- Java 21
- Spring Boot 3.3.1
- Spring Data JPA
- MySQL
- RabbitMQ
- Docker / Docker Compose
- Maven
- JUnit 5 / Mockito / MockMvc

## Endpoints
- `GET /pedidos/total/{codigoPedido}` → valor total do pedido
- `GET /pedidos/quantidade/{codigoCliente}` → número de pedidos por cliente
- `GET /pedidos/{codigoCliente}` → lista de pedidos de um cliente

## Exemplo de mensagem (JSON)
```json
{
    "codigoPedido": 1001,
    "codigoCliente": 1,
    "itens": [
        {
            "produto": "lápis",
            "quantidade": 100,
            "preco": 1.10
        },
        {
            "produto": "caderno",
            "quantidade": 10,
            "preco": 1.00
        }
    ]
}
```

## Como executar
```bash
docker compose up --build
```
RabbitMQ: http://localhost:15672 (user: guest / pass: guest)  
MySQL: localhost:3306 (user: root / pass: root)

## Rodando os testes
```bash
mvn test
```
