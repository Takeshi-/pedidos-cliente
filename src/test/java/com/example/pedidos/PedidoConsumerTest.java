package com.example.pedidos;

import com.example.pedidos.consumer.PedidoConsumer;
import com.example.pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoConsumerTest {

    @Test
    void deveCalcularValorTotalEChamarSave() throws Exception {
        String json = "{"+
          "\"codigoPedido\": 1001,"+
          "\"codigoCliente\": 1,"+
          "\"itens\": ["+
            "{\"produto\":\"lápis\",\"quantidade\":100,\"preco\":1.10},"+
            "{\"produto\":\"caderno\",\"quantidade\":10,\"preco\":1.00}"+
          "]"+
        "}";

        PedidoService service = Mockito.mock(PedidoService.class);
        ObjectMapper mapper = new ObjectMapper();
        PedidoConsumer consumer = new PedidoConsumer(service, mapper);

        consumer.consumir(json);

        var captor = ArgumentCaptor.forClass(com.example.pedidos.entity.Pedido.class);
        Mockito.verify(service).save(captor.capture());
        var pedidoSalvo = captor.getValue();

        assertEquals(new BigDecimal("120.00"), pedidoSalvo.getValorTotal());
        assertEquals(2, pedidoSalvo.getItens().size());
        assertEquals(1001, pedidoSalvo.getCodigoPedido());
        assertEquals(1, pedidoSalvo.getCodigoCliente());
    }
}
