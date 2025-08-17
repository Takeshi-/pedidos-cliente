package com.example.pedidos;

import com.example.pedidos.repository.PedidoRepository;
import com.example.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoServiceTest {

    @Test
    void retornaValorTotalDoPedido() {
        var repo = Mockito.mock(PedidoRepository.class);
        Mockito.when(repo.findValorTotalByCodigoPedido(1001)).thenReturn(new BigDecimal("120.00"));

        var service = new PedidoService(repo);

        assertEquals(new BigDecimal("120.00"), service.getValorTotal(1001));
    }
}
