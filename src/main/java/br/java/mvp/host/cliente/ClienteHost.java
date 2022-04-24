package br.java.mvp.host.cliente;

import br.java.mvp.application.service.ClienteService;
import br.java.mvp.application.exception.BusinessException;
import br.java.mvp.host.MessageError;
import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cliente", description = "Operações relacionadas ao domínio de Cliente")
@RestController
@Validated
public class ClienteHost {

    @Autowired
    private ClienteService clienteService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Criação de cliente")
    @PostMapping(value = "/cadastros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteResponse.class)))
            }, description = "CREATED"),
            @ApiResponse(responseCode = "204", description = "NO_CONTENT"),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessageError.class)))
            }, description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "422", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BusinessException.class)))
            }, description = "UNPROCESSABLE_ENTITY"),
            @ApiResponse(responseCode = "500", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessageError.class)))
            }, description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<ClienteResponse> incluirCliente(
            @Parameter(name = "cliente") @RequestBody ClienteRequest clienteRequest) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.incluirCliente(clienteRequest));
    }
}
