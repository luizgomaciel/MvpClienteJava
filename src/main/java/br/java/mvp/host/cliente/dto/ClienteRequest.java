package br.java.mvp.host.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {

    @Schema(description = "Codigo do Original/Picpay - Fixo em 100.", example = "100")
    @NonNull
    private String nome;
}
