package com.example.evertoncardoso.trabalhofinalmobile.Model;

public class Usuario {

    public static String[] colunas = new String[] { "_id", "login", "password", "nome", "telefone" };

    private String login, password, nome, telefone;

    private Long _id;

    public Usuario(){}

    public Usuario(String login, String password, String nome, String telefone)
    {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
