package Models;

import Enum.Role;

public class Usuarios {
    private String nome;
    private Role role;
    private int idade;
    private String user;
    private String password;

    public Usuarios(String nome, Role role, int idade, String user, String password) {
        this.nome = nome;
        this.role = role;
        this.idade = idade;
        this.user = user;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}