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

   private final ConsertoService service;

   public ConsertoController(ConsertoService service) {
       this.service = service;
   }

    @PostMapping("/novo-conserto")
    public ResponseEntity<Conserto> novoConserto(@RequestBody @Valid ConsertoDTO novoConserto) {
        Mecanico mecanico = new Mecanico(novoConserto.mecanicoResponsavel().nome(), novoConserto.mecanicoResponsavel().anosDeExperiencia());
        Veiculo veiculo = new Veiculo(novoConserto.veiculo().marca(), novoConserto.veiculo().modelo(), novoConserto.veiculo().ano(), novoConserto.veiculo().cor());
        Conserto conserto = new Conserto(novoConserto.dataDeEntrada(), novoConserto.dataDeSaida(), mecanico, veiculo);
        Conserto savedConserto = service.save(conserto);
        return new ResponseEntity<>(savedConserto, HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Page<Conserto>> getAllConsertos(Pageable paginacao) {
        Page<Conserto> consertos = service.findAll(paginacao);
        return new ResponseEntity<>(consertos, HttpStatus.OK);
    }

    @GetMapping("/dados-simples")
    public ResponseEntity<Page<DadosSimplesDTO>> algunsDados(Pageable paginacao){
        Page<DadosSimplesDTO> dados = service.findAllActive(paginacao).map(DadosSimplesDTO::new);
        return new ResponseEntity<>(dados, HttpStatus.OK);
    }

    @GetMapping("/conserto/{id}")
    public ResponseEntity<Conserto> getConsertoById(@PathVariable Long id){
        Optional<Conserto> consertoOptional = service.getById(id);
        return consertoOptional.map(conserto -> new ResponseEntity<>(conserto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/atualizar-parcial")
    public ResponseEntity<Conserto> updatePartial(@RequestBody @Valid DadosAtualizacaoDTO dados){
        Optional<Conserto> consertoOptional = service.getById(dados.id());
        if(consertoOptional.isPresent()) {
            service.updatePartial(dados);
            Conserto updatedConserto = service.getById(dados.id()).get();
            return new ResponseEntity<>(updatedConserto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/conserto/{id}")
    public ResponseEntity<Conserto> updateConserto(@PathVariable Long id, @RequestBody @Valid ConsertoDTO updatedConserto) {
        Optional<Conserto> consertoOptional = service.getById(id);
        if(consertoOptional.isPresent()) {
            Conserto conserto = consertoOptional.get();
            updatedConserto.updateEntity(conserto);
            service.save(conserto);
            return new ResponseEntity<>(conserto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/conserto/{id}")
    public ResponseEntity<Void> deleteConserto(@PathVariable Long id){
        service.desativarConserto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        String field = ex.getConstraintViolations().iterator().next().getPropertyPath().toString();
        String message = ex.getConstraintViolations().iterator().next().getMessage();
        return new ResponseEntity<>("Erro de validação no campo '" + field + "': " + message, HttpStatus.BAD_REQUEST);
    }
}