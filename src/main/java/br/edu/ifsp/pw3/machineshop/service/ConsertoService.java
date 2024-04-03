package br.edu.ifsp.pw3.machineshop.service;

import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsertoService {
    private final ConsertoRepository consertoRepository;

    public ConsertoService(ConsertoRepository consertoRepository) {
        this.consertoRepository = consertoRepository;
    }

    public Conserto save(Conserto conserto) {
        return consertoRepository.save(conserto);
    }


}