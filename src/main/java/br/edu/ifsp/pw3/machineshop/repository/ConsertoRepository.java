package br.edu.ifsp.pw3.machineshop.repository;

import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {
}
