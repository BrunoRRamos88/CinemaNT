package Enum;

public enum Tecnologia {
    TEC_2D(1),
    TEC_3D(2),
    TEC_4D(3),
    IMAX(4);

    private final int numero;

    Tecnologia(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
