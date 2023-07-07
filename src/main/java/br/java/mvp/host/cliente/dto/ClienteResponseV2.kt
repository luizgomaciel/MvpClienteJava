package br.java.mvp.host.cliente.dto

import br.java.mvp.domain.enums.DocumentoEnum
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Getter
import lombok.Setter

data class ClienteResponseV2(
    @Schema(description = "ID do cliente", example = "12313313")
    val id: Long? = null,

    @Schema(description = "Nome do cliente", example = "Luiz Gustavo")
    val nome: String? = null,

    @Schema(description = "Idade do cliente", example = "25")
    val idade: Int? = null,

    @Schema(description = "Estado cívil do cliente", example = "Casado, Solteiro, Outro")
    val estadoCivil: String? = null,

    @Schema(description = "Gênero do cliente", example = "Masculino, Feminino, Outro")
    val genero: String? = null,

    @Schema(description = "Naturalidade do cliente", example = "Paulista")
    val naturalidade: String? = null,

    @Schema(description = "Nacionalidade do cliente", example = "Brasileiro")
    val nacionalidade: String? = null,

    @Schema(description = "Profissao do cliente", example = "Analista de Sistemas")
    val profissao: String? = null,

    @Schema(description = "Endereco do cliente")
    val endereco: EnderecoResponse? = null,

    @Schema(description = "Documentos do cliente")
    val documentos: List<DocumentoResponse>? = null
) {

    data class EnderecoResponse(
        @Schema(description = "Tipo de Logradouro", example = "Rua, Avenida etc")
        private val tipoLogradouro: String? = null,

        @Schema(description = "Nome do Logradouro", example = "Xavier de Toledo")
        private val nomeLogradouro: String? = null,

        @Schema(description = "Número do Logradouro", example = "1050")
        private val numeroLogradouro: Int? = null,

        @Schema(description = "Cidade", example = "São Paulo")
        private val cidade: String? = null,

        @Schema(description = "Estado", example = "SP")
        private val estado: String? = null,

        @Schema(description = "CEP", example = "02563080")
        private val cep: Int? = null,
    )

    data class DocumentoResponse(
        @Schema(description = "Tipo de documento", example = "RG, CPF, CNPJ")
        val tipo: DocumentoEnum? = null,

        @Schema(description = "Número do Documento", example = "25987025847")
        val codigoDocumento: String? = null
    )
}