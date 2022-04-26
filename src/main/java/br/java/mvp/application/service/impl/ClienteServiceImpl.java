package br.java.mvp.application.service.impl;

import br.java.mvp.application.exception.BusinessException;
import br.java.mvp.application.exception.NoContentException;
import br.java.mvp.application.service.ClienteService;
import br.java.mvp.application.service.SequenceGeneratorService;
import br.java.mvp.application.service.mapper.ClienteServiceMapper;
import br.java.mvp.domain.cliente.Cliente;
import br.java.mvp.domain.cliente.ClienteRepository;
import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ClienteServiceMapper clienteServiceMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteResponse incluirCliente(ClienteRequest clienteRequest) {
        boolean isExists = clienteRepository.existsByNomeAndIdade(clienteRequest.getNome(), clienteRequest.getIdade());
        if (!isExists) {
            Cliente entity = clienteServiceMapper.toCliente(clienteRequest);
            entity.setId(sequenceGeneratorService.generateSequence(Cliente.SEQUENCE_NAME));
            entity = clienteRepository.save(entity);
            return clienteServiceMapper.toClienteResponse(entity);
        }
        throw new BusinessException("Objeto Cliente já existe cadastrado.");
    }

    @Override
    public ClienteResponse alterarCliente(Long id, ClienteRequest clienteRequest) {
        boolean isExists = clienteRepository.existsById(id);
        if (isExists) {
            Cliente entity = clienteServiceMapper.toCliente(clienteRequest);
            entity.setId(id);
            entity = clienteRepository.save(entity);
            return clienteServiceMapper.toClienteResponse(entity);
        }
        throw new BusinessException("Objeto Cliente não existe cadastrado.");
    }

    @Override
    public ClienteResponse consultarCliente(Long id) {
        Optional<Cliente> entityOpt = clienteRepository.findById(id);
        if (entityOpt.isPresent()) {
            return clienteServiceMapper.toClienteResponse(entityOpt.get());
        }
        throw new NoContentException();
    }

    @Override
    public void excluirCliente(Long id) {
        Optional<Cliente> entityOpt = clienteRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new BusinessException("Objeto Cliente não existe para exclusão.");
        }
        clienteRepository.delete(entityOpt.get());

    }

    @Override
    public Page<ClienteResponse> consultarClientes(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);
        if (!page.isEmpty()) {
            return clienteServiceMapper.toPageClienteResponse(page);
        }
        throw new NoContentException();
    }

    @Override
    public Page<ClienteResponse> consultarClienteByIdade(Integer idade, Pageable pageable) {
        Page<Cliente> page = clienteRepository.findByIdade(idade, pageable);
        if (!page.isEmpty()) {
            return clienteServiceMapper.toPageClienteResponse(page);
        }
        throw new NoContentException();
    }

}
