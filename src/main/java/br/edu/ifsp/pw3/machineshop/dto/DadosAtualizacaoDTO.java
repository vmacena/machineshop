package br.edu.ifsp.pw3.machineshop.dto;

import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDTO(@NotNull Long id, String dataDeSaida, String nomeMecanico, int anosExperiencia) {

    public DadosAtualizacaoDTO(Conserto conserto) {
        this(conserto.getId(), conserto.getDataDeSaida(), conserto.getMecanico().getNome(), conserto.getMecanico().getAnosDeExperiencia());
    }
}