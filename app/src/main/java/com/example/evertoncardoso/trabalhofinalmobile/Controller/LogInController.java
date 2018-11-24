package com.example.evertoncardoso.trabalhofinalmobile.Controller;

import com.example.evertoncardoso.trabalhofinalmobile.Database.DataBase;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.View.MainActivity;

import java.util.List;

public class LogInController {

    public static Usuario verificaLogIn(String login, String senha)
    {
        List<Usuario> lista = MainActivity.db.listarTodosUsuarios();

        for(Usuario temp: lista)
        {
            if(temp.getLogin().equals(login) && temp.getPassword().equals(senha))
            {
                return temp;
            }
        }

        return null;
    }
}
