package br.java.mvp.host;

import br.java.mvp.application.service.ClienteService;
import br.java.mvp.host.cliente.ClienteHost;
import br.java.mvp.host.mock.ClienteHostMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ClienteHostTest {

    @InjectMocks
    private ClienteHost clienteHost;

    @Mock
    private ClienteService clienteService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteHost).build();
    }

    @Test
    public void testIncluirCliente() throws Exception {
        this.mockMvc.perform(post("/v1/cadastros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ClienteHostMock.getClienteResponse()))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void testIncluirClienteException() throws Exception {
        this.mockMvc.perform(post("/v1/cadastros"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testAlterarCliente() throws Exception {
        this.mockMvc.perform(put("/v1/atualizacoes/1231")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ClienteHostMock.getClienteResponse()))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void testAlterarClienteException() throws Exception {
        this.mockMvc.perform(put("/v1/atualizacoes/1231"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testConsultarClientes() throws Exception {
        Map<String, List<String>> params = new HashMap<>();
        params.put("page", Arrays.asList("0"));
        params.put("size", Arrays.asList("10"));

        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>(params);
        this.mockMvc.perform(get("/v1/consultas").queryParams(mvm))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testConsultarClientesException() throws Exception {
        this.mockMvc.perform(get("/v1/consultas"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testConsultarClientesByIdade() throws Exception {
        Map<String, List<String>> params = new HashMap<>();
        params.put("page", Arrays.asList("0"));
        params.put("size", Arrays.asList("10"));

        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>(params);
        this.mockMvc.perform(get("/v1/consultas/12/caracteristicas").queryParams(mvm))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testConsultarClientesbyIdadeException() throws Exception {
        this.mockMvc.perform(get("/v1/consultas/12/caracteristicas"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testConsultarCliente() throws Exception {
        this.mockMvc.perform(get("/v1/consultas/1231"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testConsultarClienteException() throws Exception {
        this.mockMvc.perform(get("/v1/consultas/a1231"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testExcluirCliente() throws Exception {
        this.mockMvc.perform(delete("/v1/exclusoes/1231"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testExcluirClienteException() throws Exception {
        this.mockMvc.perform(delete("/v1/exclusoes/a1231"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }
}
