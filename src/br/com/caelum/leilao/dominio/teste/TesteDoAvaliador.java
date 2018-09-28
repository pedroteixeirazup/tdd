package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.dominio.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.servico.Avaliador;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TesteDoAvaliador {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario maria;
    private Usuario jose;

    @Before //Rodao criaAvaliador antes do Teste
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.maria =  new Usuario("Maria");
        this.jose =  new Usuario("José");
        System.out.println("cria Avaliador.");
    }


    @After
    public void finaliza() {
        System.out.println("fim");
    }

    @Test
    public  void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {

        //parte 01:  montar cenário.
         joao= new Usuario("João");
         maria = new Usuario("Maria");
         jose = new Usuario("José");



        Leilao leilao = new CriadorDeLeilao().para("Playstatio 3 Novo.")
                .lance(joao,100.0)
                .lance(maria,200.0)
                .lance(jose,300.0)
                .lance(maria,400.0)
                .constroi();
        leiloeiro.avalia(leilao);

        //parte 03: validacao.
        double maiorEsperado = 400;
        double menorEsperado = 100;
       // double avgEsperado = 333.3333333333333;
//        System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
        assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.00001);
//        System.out.println(menorEsperado == leiloeiro.getMenorLance());
        assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.00001);
////        Assert.assertEquals(avgEsperado,leiloeiro.getMediaLances(),0.00001);
////        System.out.println(avgEsperado == leiloeiro.getMediaLances());

    }

    @Test
    public void  deveEntenderLeilaoComApenasUmLance(){
        joao = new Usuario("João");
        Leilao leilao = new Leilao("PLaystation 3 novo.");

        leilao.propoe(new Lance(joao,1000.0));

       //criaAvaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000.0,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(1000.0,leiloeiro.getMenorLance(),0.00001);
    }


    @Test
    public void deveEncontrarOsTresMaioresLances(){
        joao = new Usuario("João");
        maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 novo.");

        leilao.propoe(new Lance(joao,100.0));
        leilao.propoe(new Lance(maria,200.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(maria,400.0));

        //criaAvaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores =  leiloeiro.getTresMaiores();

        assertEquals(3,maiores.size());
        assertEquals(400,maiores.get(0).getValor(),0.00001);
        assertEquals(300,maiores.get(1).getValor(),0.00001);
        assertEquals(200,maiores.get(2).getValor(),0.00001);
    }

    @Test
    public void deveEncontrarMaiorMenorValorRandom(){
        joao = new Usuario("João");
        maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 novo.");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,400.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(maria,500.0));


        //criaAvaliador();
        leiloeiro.avalia(leilao);


        assertEquals(700.0,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(120.0,leiloeiro.getMenorLance(),0.00001);
    }

    @Test
    public void deveReconheceDecrescente(){
       joao = new Usuario("João");
        maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 novo.");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        criaAvaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores =  leiloeiro.getTresMaiores();
        assertEquals(400.0,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(100.0,leiloeiro.getMenorLance(),0.00001);
    }
   

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
         joao = new Usuario("João");
         maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));

       // Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

      //  Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }

}
