package com.jfsog.MySpringBootApplication.Repositorios;

import com.jfsog.MySpringBootApplication.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
