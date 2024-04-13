package br.edu.ifsp.pw3.machineshop.dto;

import br.edu.ifsp.pw3.machineshop.entity.Mecanico;
import br.edu.ifsp.pw3.machineshop.entity.Veiculo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record ConsertoDTO(
        @Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}")
        String dataDeEntrada,
        @Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}")
        String dataDeSaida,
        @Valid
        MecanicoDTO mecanicoResponsavel,
        @Valid
        VeiculoDTO veiculo){}
