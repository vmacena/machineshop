package br.edu.ifsp.pw3.machineshop.controller;

import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsertoController {
    @Autowired
    private ConsertoRepository repository;

    @GetMapping("/listartodos")
    public List<Conserto> getAllConsertos() {
        return repository.findAll();
    }

    @PostMapping("/novoconserto")
    Conserto novoConserto(@RequestBody Conserto novoConserto) {
        return repository.save(novoConserto);
    }
}