package Models;

import Enum.Role;

public class Funcionarios extends Usuarios {
    public Funcionarios(String nome,
                        Role role,
                        int idade,
                        String user,
                        String password) {

        super(nome, role, idade, user, password);
    }
}