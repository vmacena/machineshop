package br.edu.ifsp.pw3.machineshop.dto;

import br.edu.ifsp.pw3.machineshop.entity.Conserto;

public record DadosSimplesDTO(Long id, String dataDeEntrada, String dataDeSaida, String nomeMecanico,
                              String marcaVeiculo, String modeloVeiculo) {

    public DadosSimplesDTO(Conserto conserto) {
        this(conserto.getId(), conserto.getDataDeEntrada(), conserto.getDataDeSaida(), conserto.getMecanico().getNome(),
                conserto.getVeiculo().getMarca(), conserto.getVeiculo().getModelo());
    }
}
