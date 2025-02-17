package ar.edu.unju.edm.trabajofinalfebreropv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.trabajofinalfebreropv.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

}
