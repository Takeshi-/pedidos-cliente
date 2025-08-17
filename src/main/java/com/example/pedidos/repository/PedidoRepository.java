package com.example.pedidos.repository;

import com.example.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.math.BigDecimal;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    long countByCodigoCliente(Integer codigoCliente);

    List<Pedido> findByCodigoCliente(Integer codigoCliente);

    @Query("select p.valorTotal from Pedido p where p.codigoPedido = :codigoPedido")
    BigDecimal findValorTotalByCodigoPedido(Integer codigoPedido);
}
