package co.com.toxement.transportadora.repository;

import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportadoraCredencialRepository extends JpaRepository<TransportadoraCredencial, Integer> {

    public Optional<TransportadoraCredencial> findByUsuario(String usuario);

}
