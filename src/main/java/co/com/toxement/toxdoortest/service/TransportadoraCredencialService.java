package co.com.toxement.toxdoortest.service;

import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;

import java.util.List;

public interface TransportadoraCredencialService {

    public TransportadoraCredencial getCredencialByUsuario(String usuario);

    public TransportadoraCredencial createCredencial(TransportadoraCredencial credencial);

    public List<TransportadoraCredencial> createCredenciales(List<TransportadoraCredencial> credenciales);

}
