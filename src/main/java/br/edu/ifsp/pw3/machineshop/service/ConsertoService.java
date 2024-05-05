package br.edu.ifsp.pw3.machineshop.service;

import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.exception.ResourceNotFoundException;
import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsertoService {
    private final ConsertoRepository consertoRepository;

    public ConsertoService(ConsertoRepository consertoRepository) {
        this.consertoRepository = consertoRepository;
    }

    public List<Conserto> findAll() {
        return consertoRepository.findAll();
    }

    public Page<Conserto> findAll(Pageable paginacao) {
        Page<Conserto> consertos = consertoRepository.findAll(paginacao);
        consertos.getContent().forEach(conserto -> {
            Hibernate.initialize(conserto.getMecanico().getNome());
        });
        return consertos;
    }

    public Conserto getById(Long id) {
        return consertoRepository.findById(id).orElse(null);
    }

    public Conserto save(Conserto conserto) {
        return consertoRepository.save(conserto);
    }

    public void delete(Long id) {
        if (!consertoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Conserto não encontrado com ID " + id);
        }
        consertoRepository.deleteById(id);
    }

    public Conserto desativarConserto(Long id) {
        Conserto conserto = consertoRepository.findById(id).orElse(null);
        if (conserto == null) {
            throw new ResourceNotFoundException("Conserto não encontrado com ID " + id);
        }

        conserto.desativar();
        return consertoRepository.save(conserto);
    }
}