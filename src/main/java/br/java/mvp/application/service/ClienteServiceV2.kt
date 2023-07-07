package br.java.mvp.application.service

import br.java.mvp.host.cliente.dto.ClienteRequestV2
import br.java.mvp.host.cliente.dto.ClienteResponseV2
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ClienteServiceV2 {
    fun incluirCliente(clienteRequest: ClienteRequestV2?): ClienteResponseV2?
    fun alterarCliente(id: Long?, clienteRequest: ClienteRequestV2?): ClienteResponseV2?
    fun consultarCliente(id: Long?): ClienteResponseV2?
    fun excluirCliente(id: Long?)
    fun consultarClientes(pageable: Pageable?): Page<ClienteResponseV2?>?
    fun consultarClienteByIdade(idade: Int?, pageable: Pageable?): Page<ClienteResponseV2?>?
}