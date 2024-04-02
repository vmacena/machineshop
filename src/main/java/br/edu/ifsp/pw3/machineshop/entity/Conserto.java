package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class Conserto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "DATA_DE_ENTRADA")
    private String dataDeEntrada;

    @NotNull
    @Column(name = "DATA_DE_SAIDA")
    private String dataDeSaida;

    @Embedded
    private Mecanico mecanico;

    @Embedded
    private Veiculo veiculo;
}