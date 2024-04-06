package co.com.toxement.transportadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.com.toxement.transportadora.entity.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Integer>{
	
}
