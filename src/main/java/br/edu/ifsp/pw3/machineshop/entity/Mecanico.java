package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;


@Embeddable
@AllArgsConstructor
@Data
public class Mecanico {
    @Column(name = "MECANICO_NOME")
    private String nome;

    @Column(name = "MECANICO_ANOS_DE_EXPERIENCIA")
    private int anosDeExperiencia;

    public Mecanico() {

    }

    public String getNome() {
        return nome;
    }

    public int getAnosDeExperiencia() {
        return anosDeExperiencia;
    }
}