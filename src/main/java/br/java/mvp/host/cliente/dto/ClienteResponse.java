package br.java.mvp.host.cliente.dto;

import br.java.mvp.domain.enums.DocumentoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteResponse {

    @Schema(description = "ID do cliente", example = "12313313")
    private Long id;

    @Schema(description = "Nome do cliente", example = "Luiz Gustavo")
    private String nome;

    @Schema(description = "Idade do cliente", example = "25")
    private Integer idade;

    @Schema(description = "Estado cívil do cliente", example = "Casado, Solteiro, Outro")
    private String estadoCivil;

    @Schema(description = "Gênero do cliente", example = "Masculino, Feminino, Outro")
    private String genero;

    @Schema(description = "Naturalidade do cliente", example = "Paulista")
    private String naturalidade;

    @Schema(description = "Nacionalidade do cliente", example = "Brasileiro")
    private String nacionalidade;

    @Schema(description = "Profissao do cliente", example = "Analista de Sistemas")
    private String profissao;

    @Schema(description = "Endereco do cliente")
    private EnderecoResponse endereco;

    @Schema(description = "Documentos do cliente")
    private List<DocumentoResponse> documentos;

    @Getter
    @Setter
    public static class EnderecoResponse {
        @Schema(description = "Tipo de Logradouro", example = "Rua, Avenida etc")
        private String tipoLogradouro;

        @Schema(description = "Nome do Logradouro", example = "Xavier de Toledo")
        private String nomeLogradouro;

        @Schema(description = "Número do Logradouro", example = "1050")
        private Integer numeroLogradouro;

        @Schema(description = "Cidade", example = "São Paulo")
        private String cidade;

        @Schema(description = "Estado", example = "SP")
        private String estado;

        @Schema(description = "CEP", example = "02563080")
        private Integer cep;
    }

    @Getter
    @Setter
    public static class DocumentoResponse {
        @Schema(description = "Tipo de documento", example = "RG, CPF, CNPJ")
        private DocumentoEnum tipo;

        @Schema(description = "Número do Documento", example = "25987025847")
        private String codigoDocumento;
    }
}
