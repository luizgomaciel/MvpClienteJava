package br.java.mvp.application.service;

import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;

public interface ClienteService {
    ClienteResponse incluirCliente(ClienteRequest clienteRequest);
}
