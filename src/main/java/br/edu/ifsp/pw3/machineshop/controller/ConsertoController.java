package br.edu.ifsp.pw3.machineshop.controller;

import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.service.ConsertoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ConsertoController {
    private final ConsertoService service;

    public ConsertoController(ConsertoService service) {
        this.service = service;
    }

    @PostMapping("/conserto")
    @Transactional
    public ResponseEntity<Void> saveConserto(@RequestBody @Valid Conserto conserto, BindingResult result) {
        errorHandling(result, result.getFieldError());
        service.save(conserto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/consertos")
    public List<Conserto> getAllConsertos() {
        return service.findAll();
    }

    @GetMapping("/consertos-ativos")
    public Page<Conserto> getAllActiveConsertos(Pageable paginacao) {
        return service.findAllActive(paginacao);
    }

    @GetMapping("/consertos-paginacao")
    public Page<Conserto> getAllConsertosWithPagination(Pageable paginacao) {
        return service.findAll(paginacao);
    }

    @GetMapping("/conserto/{id}")
    public ResponseEntity<Conserto> getConsertoById(@PathVariable Long id){
        Conserto conserto = service.getById(id);
        if (conserto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(conserto, HttpStatus.OK);
    }

    @PutMapping("/conserto")
    @Transactional
    public ResponseEntity<Void> updatePartial(@RequestBody @Valid DadosAtualizacaoDTO dados, BindingResult result){
        errorHandling(result, result.getFieldError());
        service.updatePartial(dados);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/conserto/{id}")
    @Transactional
    public ResponseEntity<Void> deleteConserto(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/conserto/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> deactivateConserto(@PathVariable Long id){
        service.desativarConserto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static void errorHandling(BindingResult result, FieldError result1) {
        if (result.hasErrors()) {
            String errorMessage = result1.getDefaultMessage();
            throw new ValidationException(errorMessage);
        }
    }
}