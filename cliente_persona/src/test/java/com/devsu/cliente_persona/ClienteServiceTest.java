package com.devsu.cliente_persona;

import com.devsu.cliente_persona.model.Cliente;
import com.devsu.cliente_persona.repository.ClienteRepository;
import com.devsu.cliente_persona.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setContrasena("password123");
        cliente.setEstado(true);
    }

    @Test
    void testFindById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> foundCliente = clienteRepository.findById(1L);

        assertTrue(foundCliente.isPresent(), "Cliente should be found");
        assertEquals(cliente.getId(), foundCliente.get().getId(), "Ids should match");
        assertEquals(cliente.getContrasena(), foundCliente.get().getContrasena(), "Passwords should match");
    }

    @Test
    void testSaveCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteRepository.save(cliente);

        assertNotNull(savedCliente, "Saved cliente should not be null");
        assertEquals(cliente.getId(), savedCliente.getId(), "Ids should match");
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testDeleteById() {
        doNothing().when(clienteRepository).deleteById(1L);

        clienteRepository.deleteById(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
