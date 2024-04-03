package br.edu.ifsp.pw3.machineshop.dto;

import br.edu.ifsp.pw3.machineshop.entity.Mecanico;
import br.edu.ifsp.pw3.machineshop.entity.Veiculo;

public record ConsertoDTO(
        String dataDeEntrada,
        String dataDeSaida,
        MecanicoDTO mecanicoResponsavel,
        VeiculoDTO veiculo){}
