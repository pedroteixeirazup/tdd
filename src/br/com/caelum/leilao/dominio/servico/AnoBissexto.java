package br.com.caelum.leilao.dominio.servico;

public class AnoBissexto {

    private int anoBissexto;


    public void setAnoBissexto(int anoBissexto) {
        this.anoBissexto = anoBissexto;
    }

    public boolean getAnoBissexto(int ano) {
        if (((ano % 4) == 0) && ((ano % 100) != 0)) return true;
        else if ((ano % 400) == 0) return true;
        else return false;
    }


}
