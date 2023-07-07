package br.java.mvp.application.service.mapper;

import br.java.mvp.domain.cliente.Cliente;
import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClienteServiceMapper {
    Cliente toCliente(ClienteRequest clienteRequest);

    ClienteResponse toClienteResponse(Cliente entity);

    default Page<ClienteResponse> toPageClienteResponse(Page<Cliente> page) {
        return page.map(this::toClienteResponse);
    }

}
