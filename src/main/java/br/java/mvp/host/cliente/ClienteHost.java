package br.java.mvp.host.cliente;

import br.java.mvp.application.exception.BusinessException;
import br.java.mvp.application.service.ClienteService;
import br.java.mvp.host.MessageError;
import br.java.mvp.host.cliente.dto.ClienteRequest;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @Parameter(name = "cliente") @RequestBody @Valid ClienteRequest clienteRequest)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.incluirCliente(clienteRequest));
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Atualização de cliente")
    @PutMapping(value = "/atualizacoes/{id}")
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
    public ResponseEntity<ClienteResponse> alterarCliente(
            @Parameter(name = "id", description = "ID do Cliente") @PathVariable(name = "id", required = true) Long id,
            @Parameter(name = "cliente") @RequestBody @Valid ClienteRequest clienteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.alterarCliente(id, clienteRequest));
    }

    @Operation(description = "Consulta de clientes")
    @GetMapping(value = "/consultas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Page.class)))
            }, description = "OK"),
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
    public ResponseEntity<Page<ClienteResponse>> consultarClientes(
            @Parameter(name = "page", description = "Número da página") @RequestParam("page") int pageIndex,
            @Parameter(name = "size", description = "Quantidade de itens por página") @RequestParam("size") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.consultarClientes(PageRequest.of(pageIndex, pageSize)));
    }

    @Operation(description = "Consulta de clientes")
    @GetMapping(value = "/consultas/{idade}/caracteristicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Page.class)))
            }, description = "OK"),
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
    public ResponseEntity<Page<ClienteResponse>> consultarClienteByIdade(
            @Parameter(name = "idade", description = "Idade do Cliente") @PathVariable(name = "idade", required = true) Integer idade,
            @Parameter(name = "page", description = "Número da página") @RequestParam("page") int pageIndex,
            @Parameter(name = "size", description = "Quantidade de itens por página") @RequestParam("size") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.consultarClienteByIdade(idade,PageRequest.of(pageIndex, pageSize)));
    }

    @Operation(description = "Consulta de cliente por ID")
    @GetMapping(value = "/consultas/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteResponse.class)))
            }, description = "OK"),
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
    public ResponseEntity<ClienteResponse> consultarCliente(
            @Parameter(name = "id", description = "ID do Cliente") @PathVariable(name = "id", required = true) Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.consultarCliente(id));
    }

    @Operation(description = "Excluir de cliente por ID")
    @DeleteMapping(value = "/exclusoes/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteResponse.class)))
            }, description = "OK"),
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
    public ResponseEntity<Void> excluirCliente(
            @Parameter(name = "id", description = "ID do Cliente") @PathVariable(name = "id", required = true) Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
