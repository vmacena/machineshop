package br.edu.ifsp.pw3.machineshop.service;

import br.edu.ifsp.pw3.machineshop.dto.ConsertoDTO;
import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsertoService {
    Conserto save(Conserto conserto);
    List<Conserto> findAll();
    Page<Conserto> findAllActive(Pageable paginacao);
    Page<Conserto> findAll(Pageable paginacao);
    Conserto getById(Long id);
    void updatePartial(DadosAtualizacaoDTO dados);
    void delete(Long id);
    void desativarConserto(Long id);
    void criarNovoConserto(ConsertoDTO novoConserto);
}