package br.java.mvp.application.service.impl

import br.java.mvp.application.exception.BusinessException
import br.java.mvp.application.exception.NoContentException
import br.java.mvp.application.service.ClienteServiceV2
import br.java.mvp.application.service.SequenceGeneratorServiceV2
import br.java.mvp.application.service.mapper.ClienteServiceMapperV2
import br.java.mvp.domain.cliente.Cliente
import br.java.mvp.domain.cliente.ClienteRepositoryV2
import br.java.mvp.host.cliente.dto.ClienteRequestV2
import br.java.mvp.host.cliente.dto.ClienteResponseV2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ClienteServiceV2Impl : ClienteServiceV2 {
    @Autowired
    private val sequenceGeneratorService: SequenceGeneratorServiceV2? = null

    @Autowired
    private val clienteServiceMapper: ClienteServiceMapperV2? = null

    @Autowired
    private val clienteRepository: ClienteRepositoryV2? = null

    override fun incluirCliente(clienteRequest: ClienteRequestV2?): ClienteResponseV2? {
        val isExists = clienteRepository!!.existsByNomeAndIdade(clienteRequest?.nome, clienteRequest?.idade)
        if (!isExists) {
            var entity = clienteServiceMapper!!.toCliente(clienteRequest)
            entity?.id = sequenceGeneratorService!!.generateSequence(Cliente.SEQUENCE_NAME)
            entity = clienteRepository.save(entity)
            return clienteServiceMapper.toClienteResponse(entity)
        }
        throw BusinessException("Objeto Cliente já existe cadastrado.")
    }

    override fun alterarCliente(id: Long?, clienteRequest: ClienteRequestV2?): ClienteResponseV2? {
        val isExists = clienteRepository!!.existsById(id)
        if (isExists) {
            var entity = clienteServiceMapper!!.toCliente(clienteRequest)
            entity?.id = id
            entity = clienteRepository.save(entity)
            return clienteServiceMapper.toClienteResponse(entity)
        }
        throw BusinessException("Objeto Cliente não existe cadastrado.")
    }

    override fun consultarCliente(id: Long?): ClienteResponseV2? {
        val entityOpt = clienteRepository!!.findById(id)
        if (entityOpt.isPresent) {
            return clienteServiceMapper!!.toClienteResponse(entityOpt.get())
        }
        throw NoContentException()
    }

    override fun excluirCliente(id: Long?) {
        val entityOpt = clienteRepository!!.findById(id)
        if (entityOpt.isEmpty) {
            throw BusinessException("Objeto Cliente não existe para exclusão.")
        }
        clienteRepository.delete(entityOpt.get())
    }

    override fun consultarClientes(pageable: Pageable?): Page<ClienteResponseV2?>? {
        val page = clienteRepository!!.findAll(pageable)
        if (!page.isEmpty) {
            return clienteServiceMapper!!.toPageClienteResponse(page)
        }
        throw NoContentException()
    }

    override fun consultarClienteByIdade(idade: Int?, pageable: Pageable?): Page<ClienteResponseV2?>? {
        val page = clienteRepository!!.findByIdade(idade, pageable)
        if (!page!!.isEmpty) {
            return clienteServiceMapper!!.toPageClienteResponse(page)
        }
        throw NoContentException()
    }
}