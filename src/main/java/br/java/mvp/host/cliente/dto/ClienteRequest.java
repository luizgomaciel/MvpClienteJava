package br.java.mvp.host.cliente.dto;

import br.java.mvp.domain.enums.DocumentoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ClienteRequest {

    @Schema(description = "Nome do cliente", example = "Luiz Gustavo")
    @NotBlank
    private String nome;

    @Schema(description = "Idade do cliente", example = "25")
    @NotNull
    private Integer idade;

    @Schema(description = "Estado cívil do cliente", example = "Casado, Solteiro, Outro")
    @NotBlank
    private String estadoCivil;

    @Schema(description = "Gênero do cliente", example = "Masculino, Feminino, Outro")
    @NotBlank
    private String genero;

    @Schema(description = "Naturalidade do cliente", example = "Paulista")
    @NotBlank
    private String naturalidade;

    @Schema(description = "Nacionalidade do cliente", example = "Brasileiro")
    @NotBlank
    private String nacionalidade;

    @Schema(description = "Profissao do cliente", example = "Analista de Sistemas")
    @NotBlank
    private String profissao;

    @Schema(description = "Endereco do cliente")
    @NotNull
    private EnderecoRequest endereco;

    @Schema(description = "Documentos do cliente")
    private List<DocumentoRequest> documentos;

    @Getter
    @Setter
    public static class EnderecoRequest {
        @Schema(description = "Tipo de Logradouro", example = "Rua, Avenida etc")
        @NotBlank
        private String tipoLogradouro;

        @Schema(description = "Nome do Logradouro", example = "Xavier de Toledo")
        @NotBlank
        private String nomeLogradouro;

        @Schema(description = "Número do Logradouro", example = "1050")
        @NotNull
        private Integer numeroLogradouro;

        @Schema(description = "Cidade", example = "São Paulo")
        @NotBlank
        private String cidade;

        @Schema(description = "Estado", example = "SP")
        @NotBlank
        private String estado;

        @Schema(description = "CEP", example = "02563080")
        @NotNull
        private Integer cep;
    }

    @Getter
    @Setter
    public static class DocumentoRequest {
        @Schema(description = "Tipo de documento", example = "RG, CPF, CNPJ")
        @NotNull
        private DocumentoEnum tipo;

        @Schema(description = "Número do Documento", example = "25987025847")
        @NotBlank
        private String codigoDocumento;
    }
}
