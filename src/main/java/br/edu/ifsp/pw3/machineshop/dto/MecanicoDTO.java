package br.edu.ifsp.pw3.machineshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MecanicoDTO(
        @NotBlank
        @NotNull
        String nome,
        int anosDeExperiencia
) {}