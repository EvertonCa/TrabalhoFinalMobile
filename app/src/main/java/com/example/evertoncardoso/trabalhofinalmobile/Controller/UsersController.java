package com.example.evertoncardoso.trabalhofinalmobile.Controller;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.View.MainActivity;

import java.util.List;

public class UsersController {

    public static void criaUsuario(Usuario usuario)
    {
        MainActivity.db.addUsuario(usuario);
    }

    public static void editaUsuario(Usuario usuario)
    {
        MainActivity.db.atualizaUsuario(usuario);
    }

    public static List<Usuario> retornaListaUsuarios()
    {
        return MainActivity.db.listarTodosUsuarios();
    }
}
