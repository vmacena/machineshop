package br.edu.ifsp.pw3.machineshop.dto;

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
