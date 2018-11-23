package com.example.evertoncardoso.trabalhofinalmobile.Controller;

import com.example.evertoncardoso.trabalhofinalmobile.Database.DataBase;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;

import java.util.List;

public class LogInController {

    public static Usuario verificaLogIn(DataBase db, String login, String senha)
    {
        List<Usuario> lista = db.listarTodosUsuarios();

        for(Usuario temp: lista)
        {
            if(temp.getLogin().equals(login) && temp.getPassword().equals(senha))
            {
                return temp;
            }
        }

        return null;
    }

    public static void preencheListaItems(DataBase db, Usuario usuario)
    {
        String NOME_DA_TABELA = db.criaNomeTabelaUsuario(usuario.getId());

        List<Item> items = db.listarTodosItems(NOME_DA_TABELA);

        usuario.setListaDeGastos(items);
    }
}
