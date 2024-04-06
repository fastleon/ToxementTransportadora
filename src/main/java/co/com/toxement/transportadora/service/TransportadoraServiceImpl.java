package co.com.toxement.transportadora.service;

import co.com.toxement.transportadora.entity.Transportadora;
import co.com.toxement.transportadora.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraServiceImpl implements TransportadoraService {

    @Autowired
    private TransportadoraRepository repository;

    @Override
    public List<Transportadora> getTransportadoras() {
        return (List<Transportadora>) repository.findAll();
    }

    @Override
    public Transportadora getTransportadoraById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Transportadora createTransportadora(Transportadora transportadora) {
        try {
            return repository.save(transportadora);
        } catch (Exception e) {
            return new Transportadora();
        }
    }
}
