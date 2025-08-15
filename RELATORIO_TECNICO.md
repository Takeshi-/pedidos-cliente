# Relatório Técnico - Pedido Microservice

## 1. Plano de Trabalho (Previsto vs Realizado)
### Planejado
- Dia 1: Planejamento e modelagem
- Dias 2-3: Desenvolvimento do consumidor RabbitMQ e persistência MySQL
- Dia 4: Desenvolvimento dos endpoints REST
- Dia 5: Testes automatizados
- Dia 6: Ajustes finais e documentação
- Dia 7: Entrega

### Realizado
Seguiu-se o plano com pequenos ajustes:
- Desenvolvimento e testes feitos em paralelo para agilizar a entrega
- Docker Compose configurado logo no início para ambiente completo

## 2. Tecnologias Utilizadas
- Java 21
- Spring Boot 3.3.1
- Spring Data JPA
- MySQL
- RabbitMQ
- Docker / Docker Compose
- Maven
- JUnit 5 / Mockito / MockMvc

## 3. Diagrama de Arquitetura
```
[RabbitMQ] ---> [PedidoConsumer] ---> [MySQL Database]
                                 \-> [API REST - Spring Boot]
```

## 4. Modelagem da Base de Dados
Tabela `pedidos`:
- codigoPedido (PK)
- codigoCliente
- valorTotal

Tabela `itens_pedido`:
- id (PK)
- codigo_pedido (FK -> pedidos.codigoPedido)
- produto
- quantidade
- preco

## 5. Diagrama de Implantação
```
[Docker Compose]
   ├── MySQL Container
   ├── RabbitMQ Container
   └── Pedido Microservice Container
```

## 6. Evidência de Testes
Foram criados testes unitários e de web:
- `PedidoControllerTest` (MockMvc)
- `PedidoServiceTest` (Mockito)
- `PedidoConsumerTest` (Mockito + Jackson) — valida o cálculo do total (120.00)

## 7. Repositórios
- GitHub: (adicionar link após subir)
- DockerHub: (adicionar link após publicar)
