package br.java.mvp.domain.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "cliente")
@CompoundIndexes({@CompoundIndex(name = "idx_nome", def = "{ 'nome' : 1 }", unique = false, background = true),})
public class Cliente {

    @Id
    private String id;

    @JsonProperty(value = "nome")
    private String nome;

}
