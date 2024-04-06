package co.com.toxement.transportadora.service;

import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import co.com.toxement.transportadora.repository.TransportadoraCredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraCredencialServiceImpl implements TransportadoraCredencialService{

    @Autowired
    TransportadoraCredencialRepository repository;

    @Override
    public TransportadoraCredencial getCredencialByUsuario(String usuario) {
        return repository.findByUsuario(usuario).get();
    }

    @Override
    public TransportadoraCredencial createCredencial(TransportadoraCredencial credencial) {
        return repository.save(credencial);
    }

    @Override
    public List<TransportadoraCredencial> createCredenciales(List<TransportadoraCredencial> credenciales) {
        return repository.saveAll(credenciales);
    }

}
