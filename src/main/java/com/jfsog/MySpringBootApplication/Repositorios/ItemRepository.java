package com.jfsog.MySpringBootApplication.Repositorios;

import com.jfsog.MySpringBootApplication.Entidades.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {}
