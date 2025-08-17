package com.example.pedidos.service;

import com.example.pedidos.entity.ItemPedido;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public java.math.BigDecimal getValorTotal(Integer codigoPedido) {
        return repository.findValorTotalByCodigoPedido(codigoPedido);
    }

    @Transactional(readOnly = true)
    public long getQuantidadePedidosPorCliente(Integer codigoCliente) {
        return repository.countByCodigoCliente(codigoCliente);
    }

    @Transactional(readOnly = true)
    public List<Pedido> getPedidosPorCliente(Integer codigoCliente) {
        return repository.findByCodigoCliente(codigoCliente);
    }

    @Transactional
    public Pedido save(Pedido pedido) {
        // ensure bidirectional
        if (pedido.getItens() != null) {
            for (ItemPedido i : pedido.getItens()) {
                i.setPedido(pedido);
            }
        }
        return repository.save(pedido);
    }
}
