package com.example.pedidos;

import com.example.pedidos.entity.Pedido;
import com.example.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = com.example.pedidos.controller.PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService service;

    @Test
    void deveRetornarTotalDoPedido() throws Exception {
        Mockito.when(service.getValorTotal(1001)).thenReturn(new BigDecimal("120.00"));
        mockMvc.perform(get("/pedidos/total/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("120.00"));
    }

    @Test
    void deveRetornarQuantidadePorCliente() throws Exception {
        Mockito.when(service.getQuantidadePedidosPorCliente(1)).thenReturn(2L);
        mockMvc.perform(get("/pedidos/quantidade/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void deveListarPedidosPorCliente() throws Exception {
        Pedido p = new Pedido();
        p.setCodigoPedido(1001);
        p.setCodigoCliente(1);
        p.setValorTotal(new BigDecimal("120.00"));
        Mockito.when(service.getPedidosPorCliente(1)).thenReturn(java.util.List.of(p));

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk());
    }
}
