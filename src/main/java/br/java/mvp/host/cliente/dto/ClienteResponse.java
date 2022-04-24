package br.java.mvp.host.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {

    @Schema(description = "Código do erro.", example = "ICS006")
    private String nome;
}
