package br.edu.ifsp.pw3.machineshop.dto;

import jakarta.validation.constraints.NotBlank;
public record dadosAutenticacao(
        @NotBlank
        String login,
        @NotBlank
        String senha) {
}