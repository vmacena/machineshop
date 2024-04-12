package br.edu.ifsp.pw3.machineshop.controller;

import br.edu.ifsp.pw3.machineshop.dto.ConsertoDTO;
import br.edu.ifsp.pw3.machineshop.dto.DadosSimplesDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.entity.Mecanico;
import br.edu.ifsp.pw3.machineshop.entity.Veiculo;
import br.edu.ifsp.pw3.machineshop.exception.MecanicoResponsavelNullException;
import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class ConsertoController {
    @Autowired
    private ConsertoRepository repository;

    @GetMapping("/listartodos")
    public Page<Conserto> getAllConsertos(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @GetMapping("/dadossimples")
    public List<DadosSimplesDTO> algunsDados(){
        return repository.findAll().stream().map(DadosSimplesDTO::new).toList();
    }

    @PostMapping("/novoconserto")
    @Transactional
    public ResponseEntity<String> novoConserto(@RequestBody ConsertoDTO novoConserto) {
        if (novoConserto.mecanicoResponsavel() != null) {
            Mecanico mecanico = new Mecanico(novoConserto.mecanicoResponsavel().nome(), novoConserto.mecanicoResponsavel().anosDeExperiencia());
            Veiculo veiculo = new Veiculo(novoConserto.veiculo().marca(), novoConserto.veiculo().modelo(), novoConserto.veiculo().ano(), novoConserto.veiculo().cor());
            Conserto conserto = new Conserto(novoConserto.dataDeEntrada(), novoConserto.dataDeSaida(), mecanico, veiculo);
            repository.save(conserto);
            return new ResponseEntity<>( HttpStatus.CREATED);
        } else {
            throw new MecanicoResponsavelNullException("MecanicoResponsavel não pode ser null");
        }
    }
}