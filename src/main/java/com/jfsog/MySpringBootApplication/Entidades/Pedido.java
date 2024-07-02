package com.jfsog.MySpringBootApplication.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = true)
    private Cliente cliente;
    @ManyToMany(mappedBy = "pedidos", cascade = CascadeType.MERGE)
    private List<Item> itens;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date data;
    @Min(1)
    private Double valorTotal;
}
