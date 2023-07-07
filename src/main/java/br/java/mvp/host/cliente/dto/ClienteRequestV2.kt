package br.java.mvp.host.cliente.dto

import br.java.mvp.domain.enums.DocumentoEnum
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ClienteRequestV2(
    @Schema(description = "Nome do cliente", example = "Luiz Gustavo")
    val nome: @NotBlank String? = null,

    @Schema(description = "Idade do cliente", example = "25")
    val idade: @NotNull Int? = null,

    @Schema(description = "Estado cívil do cliente", example = "Casado, Solteiro, Outro")
    val estadoCivil: @NotBlank String? = null,

    @Schema(description = "Gênero do cliente", example = "Masculino, Feminino, Outro")
    val genero: @NotBlank String? = null,

    @Schema(description = "Naturalidade do cliente", example = "Paulista")
    val naturalidade: @NotBlank String? = null,

    @Schema(description = "Nacionalidade do cliente", example = "Brasileiro")
    val nacionalidade: @NotBlank String? = null,

    @Schema(description = "Profissao do cliente", example = "Analista de Sistemas")
    val profissao: @NotBlank String? = null,

    @Schema(description = "Endereco do cliente")
    val endereco: @NotNull EnderecoRequest? = null,

    @Schema(description = "Documentos do cliente")
    val documentos: List<DocumentoRequest>? = null
) {

    data class EnderecoRequest(
        @Schema(description = "Tipo de Logradouro", example = "Rua, Avenida etc")
        val tipoLogradouro: @NotBlank String? = null,

        @Schema(description = "Nome do Logradouro", example = "Xavier de Toledo")
        val nomeLogradouro: @NotBlank String? = null,

        @Schema(description = "Número do Logradouro", example = "1050")
        val numeroLogradouro: @NotNull Int? = null,

        @Schema(description = "Cidade", example = "São Paulo")
        val cidade: @NotBlank String? = null,

        @Schema(description = "Estado", example = "SP")
        val estado: @NotBlank String? = null,

        @Schema(description = "CEP", example = "02563080")
        val cep: @NotNull Int? = null,
    )

    data class DocumentoRequest(
        @Schema(description = "Tipo de documento", example = "RG, CPF, CNPJ")
        val tipo: @NotNull DocumentoEnum? = null,

        @Schema(description = "Número do Documento", example = "25987025847")
        val codigoDocumento: @NotBlank String? = null
    )
}