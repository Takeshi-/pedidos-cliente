package com.example.pedidos.consumer;

import com.example.pedidos.dto.PedidoMensagemDTO;
import com.example.pedidos.entity.ItemPedido;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoConsumer {

    private final PedidoService pedidoService;
    private final ObjectMapper objectMapper;

    public PedidoConsumer(PedidoService pedidoService, ObjectMapper objectMapper) {
        this.pedidoService = pedidoService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${app.queue.name:pedidos.queue}")
    public void consumir(String mensagemJson) throws Exception {
        PedidoMensagemDTO dto = objectMapper.readValue(mensagemJson, PedidoMensagemDTO.class);

        BigDecimal valorTotal = dto.itens().stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(dto.codigoPedido());
        pedido.setCodigoCliente(dto.codigoCliente());
        pedido.setValorTotal(valorTotal);

        List<ItemPedido> itens = dto.itens().stream().map(i -> {
            ItemPedido ip = new ItemPedido();
            ip.setProduto(i.produto());
            ip.setQuantidade(i.quantidade());
            ip.setPreco(i.preco());
            ip.setPedido(pedido);
            return ip;
        }).collect(Collectors.toList());

        pedido.setItens(itens);

        pedidoService.save(pedido);
    }
}
