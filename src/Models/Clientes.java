package Models;

import Enum.Role;

public class Clientes extends Usuarios {
    public Clientes(String nome,
                    Role role,
                    int idade,
                    String user,
                    String password) {

        super(nome, role, idade, user, password);
    }
}