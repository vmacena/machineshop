package br.edu.ifsp.pw3.machineshop.dto;

import jakarta.validation.constraints.NotBlank;

public record MecanicoDTO(
        @NotBlank
        String nome,
        int anosDeExperiencia
    ) {}
