package br.java.mvp.application.service.mapper

import br.java.mvp.domain.cliente.ClienteV2
import br.java.mvp.host.cliente.dto.ClienteRequestV2
import br.java.mvp.host.cliente.dto.ClienteResponseV2
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.springframework.data.domain.Page

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface ClienteServiceMapperV2 {
    fun toCliente(clienteRequest: ClienteRequestV2): ClienteV2
    fun toDocumento(documento: ClienteRequestV2.DocumentoRequest) : ClienteV2.DocumentoResponse
    fun toDocumentos(documentos: List<ClienteRequestV2.DocumentoRequest>) : List<ClienteV2.DocumentoResponse>

    fun toClienteResponse(entity: ClienteV2): ClienteResponseV2
    fun toDocumento(documento: ClienteV2.DocumentoResponse) : ClienteResponseV2.DocumentoResponse
    fun toDocumentos(documentos: List<ClienteV2.DocumentoResponse>) : List<ClienteResponseV2.DocumentoResponse>

    fun toPageClienteResponse(page: Page<ClienteV2>): Page<ClienteResponseV2> {
        return page.map { entity: ClienteV2 -> toClienteResponse(entity) }
    }
}