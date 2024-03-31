package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class Mecanico {
    @Column(name = "MECANICO_NOME")
    private String nome;
    @Column(name = "MECANICO_ANOS_DE_EXPERIENCIA")
    private int anosDeExperiencia;
}