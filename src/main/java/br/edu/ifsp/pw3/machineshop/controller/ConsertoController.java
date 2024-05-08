package br.edu.ifsp.pw3.machineshop.controller;

import br.edu.ifsp.pw3.machineshop.dto.ConsertoDTO;
import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import br.edu.ifsp.pw3.machineshop.dto.DadosSimplesDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.entity.Mecanico;
import br.edu.ifsp.pw3.machineshop.entity.Veiculo;
import br.edu.ifsp.pw3.machineshop.exception.MecanicoResponsavelNullException;
import br.edu.ifsp.pw3.machineshop.exception.ValidationException;
import br.edu.ifsp.pw3.machineshop.service.ConsertoService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
public class ConsertoController {
    @Autowired
    private ConsertoService service;

    @PostMapping("/novo-conserto")
    @Transactional
    public ResponseEntity<String> novoConserto(@RequestBody @Valid ConsertoDTO novoConserto, BindingResult result) {
        errorHandling(result, result.getFieldError());

        if (novoConserto.mecanicoResponsavel() != null) {
            if(novoConserto.mecanicoResponsavel().nome() == null || novoConserto.mecanicoResponsavel().nome().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do mecânico é obrigatório e não pode ser vazio.");
            }

            Mecanico mecanico = new Mecanico(novoConserto.mecanicoResponsavel().nome(), novoConserto.mecanicoResponsavel().anosDeExperiencia());
            Veiculo veiculo = new Veiculo(novoConserto.veiculo().marca(), novoConserto.veiculo().modelo(), novoConserto.veiculo().ano(), novoConserto.veiculo().cor());
            Conserto conserto = new Conserto(novoConserto.dataDeEntrada(), novoConserto.dataDeSaida(), mecanico, veiculo);
            service.save(conserto);
            return new ResponseEntity<>( HttpStatus.CREATED);
        } else {
            throw new MecanicoResponsavelNullException("MecanicoResponsavel não pode ser null");
        }
    }

    @GetMapping("/listar-todos")
    public Page<Conserto> getAllConsertos(Pageable paginacao) {
        return service.findAll(paginacao);
    }

    @GetMapping("/dados-simples")
    public Page<DadosSimplesDTO> algunsDados(Pageable paginacao){
        return service.findAllActive(paginacao).map(DadosSimplesDTO::new);
    }

    @GetMapping("/conserto/{id}")
    public ResponseEntity<Conserto> getConsertoById(@PathVariable Long id){

        Optional<Conserto> consertoOptional = Optional.ofNullable(service.getById(id));

        return consertoOptional.map(conserto -> new ResponseEntity<>(conserto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/atualizar-parcial")
    @Transactional
    public ResponseEntity<Conserto> updatePartial(@RequestBody @Valid DadosAtualizacaoDTO dados, BindingResult result){
        errorHandling(result, result.getFieldError());

        Conserto conserto = service.getById(dados.id());
        if(conserto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(dados.nomeMecanico() != null && dados.nomeMecanico().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do mecânico é obrigatório e não pode ser vazio.");
        }

        service.updatePartial(dados);

        conserto = service.getById(dados.id());
        return new ResponseEntity<>(conserto, HttpStatus.OK);
    }

    @PutMapping("/conserto/{id}")
    @Transactional
    public ResponseEntity<Conserto> updateConserto(@PathVariable Long id, @RequestBody @Valid ConsertoDTO updatedConserto, BindingResult result) {
        errorHandling(result, result.getFieldError());

        Conserto conserto = service.getById(id);
        if (conserto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedConserto.updateEntity(conserto);
        service.save(conserto);

        return new ResponseEntity<>(conserto, HttpStatus.OK);
    }

    @DeleteMapping("/conserto/{id}")
    @Transactional
    public ResponseEntity<String> deleteConserto(@PathVariable Long id){
        service.desativarConserto(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static void errorHandling(BindingResult result, FieldError result1) {
        if (result.hasErrors()) {
            String errorMessage = result1.getDefaultMessage();
            throw new ValidationException(errorMessage);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        String field = ex.getConstraintViolations().iterator().next().getPropertyPath().toString();
        String message = ex.getConstraintViolations().iterator().next().getMessage();
        return new ResponseEntity<>("Erro de validação no campo '" + field + "': " + message, HttpStatus.BAD_REQUEST);
    }
}