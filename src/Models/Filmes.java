package Models;

import Enum.Genero;
import Enum.Tecnologia;
import java.util.List;

public class Filmes {
    private static int ultimoId = 0;
    private int id;
    private String titulo;
    private Genero genero;
    private int classificacao;
    private int tempo;
    private double valor;
    private Tecnologia tecnologia;
    private List<Poltronas> poltronasDisponiveis;

    public Filmes(String titulo, Genero genero, int classificacao, int tempo, double valor, Tecnologia tecnologia, List<Poltronas> poltronasDisponiveis) {
        this.id = ++ultimoId;
        this.titulo = titulo;
        this.genero = genero;
        this.classificacao = classificacao;
        this.tempo = tempo;
        this.valor = valor;
        this.tecnologia = tecnologia;
        this.poltronasDisponiveis = poltronasDisponiveis;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public int getTempo() {
        return tempo;
    }

    public double getValor() {
        return valor;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public List<Poltronas> getPoltronasDisponiveis() {
        return poltronasDisponiveis;
    }

    public void setPoltronasDisponiveis(List<Poltronas> poltronasDisponiveis) {
        this.poltronasDisponiveis = poltronasDisponiveis;
    }

}