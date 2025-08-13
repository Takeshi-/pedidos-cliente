package com.example.pedidos.controller;

import com.example.pedidos.entity.Pedido;
import com.example.pedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping("/total/{codigoPedido}")
    public ResponseEntity<java.math.BigDecimal> getValorTotal(@PathVariable Integer codigoPedido) {
        java.math.BigDecimal total = service.getValorTotal(codigoPedido);
        if (total == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/quantidade/{codigoCliente}")
    public ResponseEntity<Long> getQuantidadePorCliente(@PathVariable Integer codigoCliente) {
        return ResponseEntity.ok(service.getQuantidadePedidosPorCliente(codigoCliente));
    }

    @GetMapping("/{codigoCliente}")
    public ResponseEntity<List<Pedido>> getPedidosPorCliente(@PathVariable Integer codigoCliente) {
        return ResponseEntity.ok(service.getPedidosPorCliente(codigoCliente));
    }
}
