package co.com.toxement.toxdoortest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.com.toxement.toxdoortest.entity.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Integer>{
	
}
