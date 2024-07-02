package com.jfsog.MySpringBootApplication.Teste;

import com.github.javafaker.Faker;
import com.jfsog.MySpringBootApplication.Entidades.Cliente;
import com.jfsog.MySpringBootApplication.Entidades.Item;
import com.jfsog.MySpringBootApplication.Entidades.Pedido;
import com.jfsog.MySpringBootApplication.Repositorios.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TesteRepository implements ApplicationRunner {
    private static final Faker fk = new Faker();
    private final static Logger logger =
            LoggerFactory.getLogger(ApplicationRunner.class.getSimpleName());
    @Autowired
    private final ClienteRepository clienteRepository;
    public TesteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Iniciando carga de dados");
        Stream.generate(()->0)
              .limit(5)
              .forEach(n -> clienteRepository.saveAndFlush(generateCliente()));
    }
    public Item generateItem() {
        return new Item(null, fk.name().name(), fk.number().randomDouble(4, 0, 50000));
    }
    public Pedido generatePedido() {
        var list = Stream.generate(this::generateItem).limit(3).collect(Collectors.toList());
        var total = list.stream().mapToDouble(Item::getPreco).sum();
        return Pedido.builder().data(LocalDateTime.now()).valorTotal(total).itens(list).build();
    }
    public Cliente generateCliente() {
        var list = Stream.generate(this::generatePedido).limit(4).toList();
        var cliente = Cliente.builder()
                             .nome(fk.name().name())
                             .endereco(fk.address().fullAddress())
                             .pedidos(list)
                             .id(fk.random().nextLong(80))
                             .build();
        logger.info("%s%s%s".formatted(">".repeat(7), cliente, "<".repeat(7)));
        cliente.setPedidos(list);
        return cliente;
    }
}
