package br.java.mvp.service;

import br.java.mvp.MvpClienteJavaApplication;
import br.java.mvp.application.exception.BusinessException;
import br.java.mvp.application.exception.NoContentException;
import br.java.mvp.application.service.SequenceGeneratorService;
import br.java.mvp.application.service.impl.ClienteServiceImpl;
import br.java.mvp.application.service.mapper.ClienteServiceMapper;
import br.java.mvp.domain.cliente.ClienteRepository;
import br.java.mvp.host.cliente.dto.ClienteResponse;
import br.java.mvp.service.mock.ClienteServiceMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {MvpClienteJavaApplication.class})
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private ClienteServiceMapper clienteServiceMapper;

    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIncluirClienteSucesso() {
        Mockito.when(clienteRepository.existsByNomeAndIdade(any(), any())).thenReturn(Boolean.FALSE);
        Mockito.when(clienteServiceMapper.toCliente(any())).thenReturn(ClienteServiceMock.getClienteMapper());
        Mockito.when(sequenceGeneratorService.generateSequence(any())).thenReturn(1L);
        Mockito.when(clienteRepository.save(any())).thenReturn(ClienteServiceMock.getClienteMapper());
        Mockito.when(clienteServiceMapper.toClienteResponse(any())).thenReturn(ClienteServiceMock.getClienteResponse());

        ClienteResponse response = clienteServiceImpl.incluirCliente(ClienteServiceMock.getClienteRequest());
        assertTrue(Objects.nonNull(response));
    }

    @Test
    public void testIncluirClienteBusinessException() {
        Mockito.when(clienteRepository.existsByNomeAndIdade(any(), any())).thenReturn(Boolean.TRUE);
        Exception exception = (Exception) Assertions.assertThrows(RuntimeException.class, () -> {
            clienteServiceImpl.incluirCliente(ClienteServiceMock.getClienteRequest());
        });
        assertTrue(exception instanceof BusinessException);
    }

    @Test
    public void testAlterarClienteSucesso() {
        Mockito.when(clienteRepository.existsById(any())).thenReturn(Boolean.TRUE);
        Mockito.when(clienteServiceMapper.toCliente(any())).thenReturn(ClienteServiceMock.getClienteMapper());
        Mockito.when(clienteRepository.save(any())).thenReturn(ClienteServiceMock.getClienteMapper());
        Mockito.when(clienteServiceMapper.toClienteResponse(any())).thenReturn(ClienteServiceMock.getClienteResponse());

        ClienteResponse response = clienteServiceImpl.alterarCliente(1L, ClienteServiceMock.getClienteRequest());
        assertTrue(Objects.nonNull(response));
    }

    @Test
    public void testAlterarClienteBusinessException() {
        Mockito.when(clienteRepository.existsById(any())).thenReturn(Boolean.FALSE);

        Exception exception = (Exception) Assertions.assertThrows(RuntimeException.class, () -> {
            clienteServiceImpl.alterarCliente(1L, ClienteServiceMock.getClienteRequest());
        });
        assertTrue(exception instanceof BusinessException);
    }

    @Test
    public void testConsultarClienteSucesso() {
        Mockito.when(clienteRepository.findById(any())).thenReturn(ClienteServiceMock.getClienteOpt());
        Mockito.when(clienteServiceMapper.toClienteResponse(any())).thenReturn(ClienteServiceMock.getClienteResponse());

        ClienteResponse response = clienteServiceImpl.consultarCliente(1L);
        assertTrue(Objects.nonNull(response));
    }

    @Test
    public void testConsultarClienteNoContentException() {
        Mockito.when(clienteRepository.findById(any())).thenReturn(Optional.empty());

        Exception exception = (Exception) Assertions.assertThrows(RuntimeException.class, () -> {
            clienteServiceImpl.consultarCliente(1L);
        });
        assertTrue(exception instanceof NoContentException);
    }

    @Test
    public void testExcluirClienteSucesso() {
        Mockito.when(clienteRepository.findById(any())).thenReturn(ClienteServiceMock.getClienteOpt());
        clienteServiceImpl.excluirCliente(1L);
        assertTrue(Boolean.TRUE);
    }

    @Test
    public void testExcluirClienteBusinessException() {
        Mockito.when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = (Exception) Assertions.assertThrows(RuntimeException.class, () -> {
            clienteServiceImpl.excluirCliente(1L);
        });
        assertTrue(exception instanceof BusinessException);
    }

    @Test
    public void testConsultarClientesSucesso() {
        Mockito.when(clienteRepository.findAll(any(Pageable.class))).thenReturn(ClienteServiceMock.getPageableCliente());
        Mockito.when(clienteServiceMapper.toPageClienteResponse(any())).thenReturn(ClienteServiceMock.getPageableClienteResponse());

        Page<ClienteResponse> response = clienteServiceImpl.consultarClientes(Pageable.unpaged());
        assertTrue(Objects.nonNull(response));
    }

    @Test
    public void testConsultarClientesNoContentException() {
        Mockito.when(clienteRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        Exception exception = (Exception) Assertions.assertThrows(RuntimeException.class, () -> {
            clienteServiceImpl.consultarClientes(Pageable.unpaged());
        });
        assertTrue(exception instanceof NoContentException);
    }

    @Test
    public void testConsultarClienteByIdadeSucesso() {
        Mockito.when(clienteRepository.findByIdade(any(), any(Pageable.class))).thenReturn(ClienteServiceMock.getPageableCliente());
        Mockito.when(clienteServiceMapper.toPageClienteResponse(any())).thenReturn(ClienteServiceMock.getPageableClienteResponse());

        Page<ClienteResponse> response = clienteServiceImpl.consultarClienteByIdade(25, Pageable.unpaged());
        assertTrue(Objects.nonNull(response));
    }

    @Test
    public void testConsultarClienteByIdadeNoContentException() {
        Mockito.when(clienteRepository.findByIdade(any(), any(Pageable.class))).thenReturn(Page.empty());
        Exception exception = (Exception) Assertions.assertThrows(RuntimeException.class, () -> {
            clienteServiceImpl.consultarClienteByIdade(25, Pageable.unpaged());
        });
        assertTrue(exception instanceof NoContentException);
    }

}
