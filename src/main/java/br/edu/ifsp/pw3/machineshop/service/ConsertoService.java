package br.edu.ifsp.pw3.machineshop.service;

import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.exception.ResourceNotFoundException;
import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsertoService {

    private final ConsertoRepository consertoRepository;

    public ConsertoService(ConsertoRepository consertoRepository) {
        this.consertoRepository = consertoRepository;
    }

    @Transactional
    public Conserto save(Conserto conserto) { return consertoRepository.save(conserto); }

    public Page<Conserto> findAllActive(Pageable paginacao) {
        return consertoRepository.findAllByAtivoTrue(paginacao);
    }

    public Page<Conserto> findAll(Pageable paginacao) {
       return consertoRepository.findAll(paginacao);
    }

    public Optional<Conserto> getById(Long id) {
        return consertoRepository.findById(id);
    }

    public void updatePartial(DadosAtualizacaoDTO dados) {
        Conserto conserto = consertoRepository.getReferenceById(dados.id());
        conserto.atualizarInformacoes(dados);
    }

    public void desativarConserto(Long id) {
        Conserto conserto = consertoRepository.findById(id).orElse(null);
        if (conserto == null) {
            throw new ResourceNotFoundException("Conserto não encontrado com ID " + id);
        }

        conserto.desativar();
        consertoRepository.save(conserto);
    }
}