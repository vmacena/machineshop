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

    @Column(name = "VEICULO_MARCA")
    private String marca;
    @Column(name = "VEICULO_MODELO")
    private String modelo;
    @Column(name = "VEICULO_ANO")
    private String ano;
    @Column(name = "VEICULO_COR")
    private String cor;

    public Veiculo() {}

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }
}