package br.java.mvp.service.mock;

import br.java.mvp.application.service.mapper.ClienteServiceMapper;
import br.java.mvp.domain.cliente.Cliente;
import br.java.mvp.domain.enums.DocumentoEnum;
import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteServiceMock {

    public static ClienteRequest getClienteRequest() {
        ClienteRequest request = new ClienteRequest();

        List<ClienteRequest.DocumentoRequest> documentos = new ArrayList<ClienteRequest.DocumentoRequest>();
        ClienteRequest.DocumentoRequest documentoCpf = new ClienteRequest.DocumentoRequest();
        documentoCpf.setTipo(DocumentoEnum.CPF);
        documentoCpf.setCodigoDocumento("1213223");

        ClienteRequest.DocumentoRequest documentoRg = new ClienteRequest.DocumentoRequest();
        documentoRg.setTipo(DocumentoEnum.RG);
        documentoRg.setCodigoDocumento("121323132123");
        documentos.add(documentoRg);

        documentos.add(documentoCpf);
        documentos.add(documentoRg);

        ClienteRequest.EnderecoRequest endereco = new ClienteRequest.EnderecoRequest();
        endereco.setCep(95245080);
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setNomeLogradouro("Xavier de Toledo");
        endereco.setNumeroLogradouro(1200);
        endereco.setTipoLogradouro("Rua");

        request.setEndereco(endereco);
        request.setDocumentos(documentos);
        request.setEstadoCivil("Casado");
        request.setGenero("Masculino");
        request.setIdade(20);
        request.setNaturalidade("Paulista");
        request.setNacionalidade("Brasileiro");
        request.setProfissao("Analista de Sistemas");
        request.setNome("Luiz Gustavo");
        return request;
    }

    public static Cliente getClienteMapper() {
        ClienteServiceMapper mapper = Mappers.getMapper(ClienteServiceMapper.class);
        return mapper.toCliente(getClienteRequest());
    }

    public static ClienteResponse getClienteResponse() {
        ClienteServiceMapper mapper = Mappers.getMapper(ClienteServiceMapper.class);
        return mapper.toClienteResponse(getClienteMapper());
    }

    public static Optional<Cliente> getClienteOpt() {
        return Optional.of(getClienteMapper());
    }

    public static Page<Cliente> getPageableCliente() {
        List<Cliente> lista = new ArrayList<Cliente>();
        lista.add(getClienteMapper());
        return new PageImpl<Cliente>(lista);
    }

    public static Page<ClienteResponse> getPageableClienteResponse() {
        List<ClienteResponse> lista = new ArrayList<ClienteResponse>();
        lista.add(getClienteResponse());
        return new PageImpl<ClienteResponse>(lista);
    }

}
