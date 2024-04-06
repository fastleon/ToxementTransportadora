package co.com.toxement.transportadora.service;

import co.com.toxement.transportadora.entity.TransportadoraCredencial;

import java.util.List;

public interface TransportadoraCredencialService {

    public TransportadoraCredencial getCredencialByUsuario(String usuario);

    public TransportadoraCredencial createCredencial(TransportadoraCredencial credencial);

    public List<TransportadoraCredencial> createCredenciales(List<TransportadoraCredencial> credenciales);

}
