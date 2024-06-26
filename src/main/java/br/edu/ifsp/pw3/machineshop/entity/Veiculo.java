package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Embeddable
@AllArgsConstructor
@Data
public class Veiculo {

    @Getter
    @Column(name = "VEICULO_MARCA")
    private String marca;
    @Getter
    @Column(name = "VEICULO_MODELO")
    private String modelo;
    @Column(name = "VEICULO_ANO")
    private String ano;
    @Getter
    @Column(name = "VEICULO_COR")
    private String cor;

    public Veiculo() {}

}