package br.java.mvp.domain.cliente;

import br.java.mvp.domain.enums.DocumentoEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "cliente")
@CompoundIndexes({@CompoundIndex(name = "idx_idade", def = "{ 'idade' : 1 }", unique = false, background = true),})
public class Cliente {

    @Transient
    public static final String SEQUENCE_NAME = "cliente_sequence";

    @Id
    private Long id;
    private String nome;
    private Integer idade;
    private String estadoCivil;
    private String genero;
    private String naturalidade;
    private String nacionalidade;
    private String profissao;
    private EnderecoResponse endereco;
    private List<DocumentoResponse> documentos;

    @Getter
    @Setter
    public static class EnderecoResponse {
        private String tipoLogradouro;
        private String nomeLogradouro;
        private Integer numeroLogradouro;
        private String cidade;
        private String estado;
        private Integer cep;
    }

    @Getter
    @Setter
    public static class DocumentoResponse {
        private DocumentoEnum tipo;
        private String codigoDocumento;
    }

}
