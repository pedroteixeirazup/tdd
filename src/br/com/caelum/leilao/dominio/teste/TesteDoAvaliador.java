package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.dominio.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

public class TesteDoAvaliador {
    @Test
    public  void deveEntenderLancesEmOrdemCrescente() {

        //parte 01:  montar cenário.
        Usuario joao= new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario jose = new Usuario("José");


        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,250.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,400.0));
        //parte 02: acao.
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //parte 03: validacao.
        double maiorEsperado = 400;
        double menorEsperado = 250;
        double avgEsperado = 333.3333333333333;
        System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
        Assert.assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.00001);
        System.out.println(menorEsperado == leiloeiro.getMenorLance());
        Assert.assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.00001);
        Assert.assertEquals(avgEsperado,leiloeiro.getMediaLances(),0.00001);
        System.out.println(avgEsperado == leiloeiro.getMediaLances());
    }
}
