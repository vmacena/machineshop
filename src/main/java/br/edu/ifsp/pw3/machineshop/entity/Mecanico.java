package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Embeddable
@AllArgsConstructor
@Data
public class Mecanico {
    @NotNull
    @Column(name = "MECANICO_NOME")
    private String nome;

    @NotNull
    @Column(name = "MECANICO_ANOS_DE_EXPERIENCIA")
    private int anosDeExperiencia;

    public Mecanico() {

    }
}