package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@AllArgsConstructor
@Data
public class Veiculo {

    @NotNull
    @Column(name = "VEICULO_MARCA")
    private String marca;

    @NotNull
    @Column(name = "VEICULO_MODELO")
    private String modelo;

    @NotNull
    @Column(name = "VEICULO_ANO")
    private int ano;

    public Veiculo() {}

}