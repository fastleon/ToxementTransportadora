package co.com.toxement.toxdoortest.service;

import co.com.toxement.toxdoortest.entity.Transportadora;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransportadoraService {
    //read all tranportadoras
    public List<Transportadora> getTransportadoras();

    //read transportadora por id
    Transportadora getTransportadoraById(Integer id);

    Transportadora createTransportadora(Transportadora transportadora);

}
