package com.example.pedidos.dto;

import java.math.BigDecimal;

public record ItemPedidoDTO(
    String produto,
    Integer quantidade,
    BigDecimal preco
) {}
