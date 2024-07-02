package com.jfsog.MySpringBootApplication.Repositorios;

import com.jfsog.MySpringBootApplication.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
