package com.example.pedidos.dto;

import java.util.List;

public record PedidoMensagemDTO(
    Integer codigoPedido,
    Integer codigoCliente,
    List<ItemPedidoDTO> itens
) {}
