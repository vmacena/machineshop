package br.edu.ifsp.pw3.machineshop.entity;

import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Entity
@AllArgsConstructor
@Data
public class Conserto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "DATA_DE_ENTRADA")
    private String dataDeEntrada;

    @Getter
    @Column(name = "DATA_DE_SAIDA")
    private String dataDeSaida;

    private boolean ativo = true;

    @Getter
    @Embedded
    private Mecanico mecanico;
    @Getter
    @Embedded
    private Veiculo veiculo;

    public Conserto() {
    }

    public Conserto(@NotNull String dataDeEntrada, @NotNull String dataDeSaida, Mecanico mecanico, Veiculo veiculo) {
        this.dataDeEntrada = dataDeEntrada;
        this.dataDeSaida = dataDeSaida;
        this.mecanico = mecanico;
        this.veiculo = veiculo;
    }

    public void atualizarInformacoes(DadosAtualizacaoDTO dados) {
        if (dados.dataDeSaida() != null)
            this.dataDeSaida = dados.dataDeSaida();

        this.mecanico.atualizarInformacoes(dados.nomeMecanico(), dados.anosExperiencia());
    }

    public void desativar() {
        this.ativo = false;
    }

}