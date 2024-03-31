package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Conserto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATA_DE_ENTRADA")
    private String dataDeEntrada;
    @Column(name = "DATA_DE_SAIDA")
    private String dataDeSaida;
    @Embedded
    private Mecanico mecanico;
    @Embedded
    private Veiculo veiculo;
}