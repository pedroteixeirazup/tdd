package br.com.caelum.leilao.dominio.servico;


import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.List;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double mediaLances = 0.0;

    public void avalia(Leilao leilao){
      for(Lance lance: leilao.getLances()){
          if(lance.getValor() > maiorDeTodos)
                maiorDeTodos = lance.getValor();
          if(lance.getValor() < menorDeTodos)
              menorDeTodos = lance.getValor();
      }
  }
    public void avaliaMedia(Leilao leilao){
        for(Lance lance: leilao.getLances()){
            mediaLances = mediaLances + lance.getValor();
        }
        mediaLances = mediaLances/leilao.getLances().size();
    }

    public double getMediaLances() {
        return mediaLances;
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }
}
