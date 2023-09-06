package Models;

import Models.Filmes;
import Models.Poltronas;

public class Ingressos {
    private Filmes filmes;
    private Poltronas poltronas;
    private Clientes clientes;

    public Ingressos(Filmes filmes, Poltronas poltronas, Clientes clientes) {
        this.filmes = filmes;
        this.poltronas = poltronas;
        this.clientes = clientes;
    }

    public Filmes getFilme() {
        return filmes;
    }

    public Poltronas getPoltrona() {
        return poltronas;
    }

    public Clientes getCliente() {
        return clientes;
    }
}
