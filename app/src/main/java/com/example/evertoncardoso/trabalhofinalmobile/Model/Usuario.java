package com.example.evertoncardoso.trabalhofinalmobile.Model;

import java.util.List;

public class Usuario {

    private String login, password, nome, telefone, email;

    private String enderecoFotos = "";

    private int id;

    private List<Item> listaDeGastos;

    public Usuario(){}

    public Usuario(String login, String password, String nome, String telefone, String email)
    {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Usuario(String login, String password, String nome, String telefone, String email,  String enderecoFotos)
    {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.enderecoFotos = enderecoFotos;
    }

    public Usuario(int id, String login, String password, String nome, String telefone, String email)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Usuario(int id, String login, String password, String nome, String telefone, String email, String enderecoFotos)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.enderecoFotos = enderecoFotos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getListaDeGastos() {
        return listaDeGastos;
    }

    public void setListaDeGastos(List<Item> listaDeGastos) {
        this.listaDeGastos = listaDeGastos;
    }

    public String getEnderecoFotos() {
        return enderecoFotos;
    }

    public void setEnderecoFotos(String enderecoFotos) {
        this.enderecoFotos = enderecoFotos;
    }
}
