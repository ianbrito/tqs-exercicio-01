/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Midias
 */
public class GerenciadoraClientesTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private GerenciadoraClientes gerenciadoraClientes;

    private int idCliente01 = 1;
    private int idCliente02 = 2;

    @Before
    public void setUp() {

        List<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true));
        clientes.add(new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true));

        this.gerenciadoraClientes = new GerenciadoraClientes(clientes);
    }

    public GerenciadoraClientesTest() {
    }

    @Test
    public void testPesquisaClienteExistente() {

        Cliente cliente = this.gerenciadoraClientes.pesquisaCliente(1);

        assertEquals(cliente.getId(), 1);
        assertEquals(cliente.getEmail(), "gugafarias@gmail.com");
    }

    @Test
    public void testPesquisaClienteInexistente() {

        Cliente cliente = this.gerenciadoraClientes.pesquisaCliente(4);

        assertNull(cliente);
    }

    @Test
    public void testRemoveClienteExistente() {
        boolean success = this.gerenciadoraClientes.removeCliente(idCliente01);
        assertTrue(success);
    }

    @Test
    public void testRemoveClienteInexistente() {
        boolean success = this.gerenciadoraClientes.removeCliente(4);
        assertFalse(success);
    }

    @Test
    public void testValidadeIdadeValida() throws Exception {
        Cliente cliente = new Cliente(1, "Ian Brito", 18, "ian@gmail.com", 1, true);
        assertTrue(this.gerenciadoraClientes.validaIdade(cliente.getIdade()));
    }

    @Test
    public void testValidaIdadeInvalidaMetodo1() throws Exception {
        exception.expect(IdadeNaoPermitidaException.class);
        Cliente cliente = new Cliente(1, "Ian Brito", 17, "ian@gmail.com", 1, true);
        this.gerenciadoraClientes.validaIdade(cliente.getIdade());
    }

    @Test
    public void testValidaIdadeInvalidaMetodo2() throws Exception {
        try {
            Cliente cliente = new Cliente(1, "Ian Brito", 17, "ian@gmail.com", 1, true);
            this.gerenciadoraClientes.validaIdade(cliente.getIdade());
        } catch (Exception e) {
            assertTrue(e instanceof IdadeNaoPermitidaException);
        }
    }
}
