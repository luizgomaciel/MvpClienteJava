package br.java.mvp.application.service;

import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    ClienteResponse incluirCliente(ClienteRequest clienteRequest);

    ClienteResponse alterarCliente(Long id, ClienteRequest clienteRequest);

    ClienteResponse consultarCliente(Long id);

    void excluirCliente(Long id);

    Page<ClienteResponse> consultarClientes(Pageable pageable);

    Page<ClienteResponse>  consultarClienteByIdade(Integer idade, Pageable pageable);
}
