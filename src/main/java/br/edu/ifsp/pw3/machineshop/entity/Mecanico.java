package br.edu.ifsp.pw3.machineshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Getter
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

    public void atualizarInformacoes(String nomeMecanico, Integer anosExperiencia) {
        if(nomeMecanico != null)
            this.nome = nomeMecanico;

        if(anosExperiencia != null)
            this.anosDeExperiencia = anosExperiencia;
    }
}