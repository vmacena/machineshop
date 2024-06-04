package br.edu.ifsp.pw3.machineshop.service;

import br.edu.ifsp.pw3.machineshop.dto.ConsertoDTO;
import br.edu.ifsp.pw3.machineshop.dto.DadosAtualizacaoDTO;
import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import br.edu.ifsp.pw3.machineshop.entity.Mecanico;
import br.edu.ifsp.pw3.machineshop.entity.Veiculo;
import br.edu.ifsp.pw3.machineshop.exception.ResourceNotFoundException;
import br.edu.ifsp.pw3.machineshop.repository.ConsertoRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsertoServiceImpl implements ConsertoService {
    private final ConsertoRepository consertoRepository;

    public ConsertoServiceImpl(ConsertoRepository consertoRepository) {
        this.consertoRepository = consertoRepository;
    }

    public void criarNovoConserto(ConsertoDTO novoConserto) {
        Mecanico mecanico = new Mecanico(novoConserto.mecanicoResponsavel().nome(), novoConserto.mecanicoResponsavel().anosDeExperiencia());
        Veiculo veiculo = new Veiculo(novoConserto.veiculo().marca(), novoConserto.veiculo().modelo(), novoConserto.veiculo().ano(), novoConserto.veiculo().cor());
        Conserto conserto = new Conserto(novoConserto.dataDeEntrada(), novoConserto.dataDeSaida(), mecanico, veiculo);
        save(conserto);
    }

    @Override
    public Conserto save(Conserto conserto) {
        return consertoRepository.save(conserto);
    }

    @Override
    public List<Conserto> findAll() {
        return consertoRepository.findAll();
    }

    @Override
    public Page<Conserto> findAllActive(Pageable paginacao) {
        Page<Conserto> consertos = consertoRepository.findAllByAtivoTrue(paginacao);
        initializeMecanicos(consertos);
        return consertos;
    }

    @Override
    public Page<Conserto> findAll(Pageable paginacao) {
        Page<Conserto> consertos = consertoRepository.findAll(paginacao);
        initializeMecanicos(consertos);
        return consertos;
    }

    private void initializeMecanicos(Page<Conserto> consertos) {
        consertos.getContent().forEach(conserto -> {
            Hibernate.initialize(conserto.getMecanico().getNome());
        });
    }

    @Override
    public Conserto getById(Long id) {
        return consertoRepository.findById(id).orElse(null);
    }

    @Override
    public void updatePartial(DadosAtualizacaoDTO dados) {
        Conserto conserto = consertoRepository.getReferenceById(dados.id());
        if (conserto.getMecanico() == null) {
            conserto.setMecanico(new Mecanico(null, 0));
        }
        conserto.atualizarInformacoes(dados);
    }

    @Override
    public void delete(Long id) {
        if (!consertoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Conserto não encontrado com ID " + id);
        }
        consertoRepository.deleteById(id);
    }

    @Override
    public void desativarConserto(Long id) {
        Conserto conserto = consertoRepository.findById(id).orElse(null);
        if (conserto == null) {
            throw new ResourceNotFoundException("Conserto não encontrado com ID " + id);
        }

        conserto.desativar();
        consertoRepository.save(conserto);
    }
}