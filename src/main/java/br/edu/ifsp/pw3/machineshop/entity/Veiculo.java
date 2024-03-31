package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Veiculo {
    @Column(name = "VEICULO_MARCA")
    private String marca;
    @Column(name = "VEICULO_MODELO")
    private String modelo;
    @Column(name = "VEICULO_ANO")
    private int ano;
}