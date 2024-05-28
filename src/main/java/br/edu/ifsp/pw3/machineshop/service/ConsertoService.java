package br.edu.ifsp.pw3.machineshop.service;

import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.exception.ResourceNotFoundException;
import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ConsertoService {
    void save(Conserto conserto);
    List<Conserto> findAll();
    Page<Conserto> findAllActive(Pageable paginacao);
    Page<Conserto> findAll(Pageable paginacao);
    Conserto getById(Long id);
    void updatePartial(DadosAtualizacaoDTO dados);
    void delete(Long id);
    void desativarConserto(Long id);
}