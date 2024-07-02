package com.jfsog.MySpringBootApplication.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Propriedade {
    @Id
    private String nome;
    private String valor;
    private String descricao;
    private String categoria;
    private String subcategoria;
}
