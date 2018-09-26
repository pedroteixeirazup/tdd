package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.servico.Palindromo;
import org.junit.Assert;
import org.junit.Test;


public class PalindromoTeste {
    @Test
    public void deveIdentificarPalindromoEFiltrarCaracteresInvalidos() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
                "Socorram-me subi no onibus em Marrocos");
        Assert.assertTrue(resultado);
    }

    @Test
    public void deveIdentificarPalindromo() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
                "Anotaram a data da maratona");
        Assert.assertTrue(resultado);
    }

    @Test
    public void deveIdentificarSeNaoEhPalindromo() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
                "E preciso amar as pessoas como se nao houvesse amanha");
        Assert.assertFalse(resultado);
    }
}

