package co.com.toxement.toxdoortest.repository;

import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportadoraCredencialRepository extends JpaRepository<TransportadoraCredencial, Integer> {

    public Optional<TransportadoraCredencial> findByUsuario(String usuario);

}
