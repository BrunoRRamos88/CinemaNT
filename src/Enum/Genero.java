package Enum;

public enum Genero {

    ACAO(1),
    AVENTURA(2),
    COMEDIA(3),
    DRAMA(4),
    FICCAO_CIENTIFICA(5),
    ROMANCE(6),
    SUSPENSE(7),
    TERROR(8);

    private final int numero;

    Genero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}