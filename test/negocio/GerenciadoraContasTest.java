/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ian
 */
public class GerenciadoraContasTest {

    private GerenciadoraContas gerenciadoraContas;

    public GerenciadoraContasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<ContaCorrente> contasDoBanco = new ArrayList<>();

        contasDoBanco.add(new ContaCorrente(1, 100, true));
        contasDoBanco.add(new ContaCorrente(2, 0, true));
        contasDoBanco.add(new ContaCorrente(3, 1000, true));

        this.gerenciadoraContas = new GerenciadoraContas(contasDoBanco);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTransfereValor() {
        int contaOrigem = 1;
        int contaDestino = 2;
        
        boolean success = this.gerenciadoraContas.transfereValor(contaOrigem, 50, contaDestino);
        
        assertTrue(success);
    }
    
    /**
     * Test Transfere Valor Valor Maior Que O Saldo
     */
    @Test
    public void testTransfereValorValorMaiorQueOSaldo() {
        int contaOrigem = 1;
        int contaDestino = 2;
        
        boolean success = this.gerenciadoraContas.transfereValor(contaOrigem, 1000, contaDestino);
        
        assertFalse(success);
    }
    
    
    @Test
    public void testTransfereValorContaSemSaldo() {
        int contaOrigem = 2;
        int contaDestino = 1;
        
        boolean success = this.gerenciadoraContas.transfereValor(contaOrigem, 100, contaDestino);
        
        assertFalse(success);
    }
    
    @Test
    public void testTransfereValorContaOrigemInexistente() {
        int contaOrigem = 4;
        int contaDestino = 1;
        
        boolean success = this.gerenciadoraContas.transfereValor(contaOrigem, 100, contaDestino);
        
        assertFalse(success);
    }
    
    @Test
    public void testTransfereValorContaDestinoInexistente() {
        int contaOrigem = 1;
        int contaDestino = 4;
        
        boolean success = this.gerenciadoraContas.transfereValor(contaOrigem, 100, contaDestino);
        
        assertFalse(success);
    }
}
