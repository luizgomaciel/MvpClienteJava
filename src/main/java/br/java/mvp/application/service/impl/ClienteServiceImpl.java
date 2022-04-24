package br.java.mvp.application.service.impl;

import br.java.mvp.application.service.ClienteService;
import br.java.mvp.application.service.mapper.ClienteServiceMapper;
import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteServiceMapper clienteServiceMapper;

    @Override
    public ClienteResponse incluirCliente(ClienteRequest clienteRequest) {
        return null;
    }
}
