package com.jfsog.MySpringBootApplication.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
    private String nome;
    @NotNull
    @Size(
            min = 2,
            max = 300,
            message = "O tamanho do endereço deve ser entre {min} e {max} caracteres"
    )
    private String endereco;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        pedidos.forEach(p -> p.setCliente(this));
    }
}
