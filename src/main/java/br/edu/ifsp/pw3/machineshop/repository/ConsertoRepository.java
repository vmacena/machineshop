package br.edu.ifsp.pw3.machineshop.repository;

import br.edu.ifsp.pw3.machineshop.entity.Conserto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {

    Page<Conserto> findAllByAtivoTrue(Pageable pageable);
}
