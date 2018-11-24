package com.example.evertoncardoso.trabalhofinalmobile.Controller;

import com.example.evertoncardoso.trabalhofinalmobile.Database.DataBase;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.View.MainActivity;

import java.util.List;

public class ItemsController {

    public static void criaItem(Item item)
    {
        MainActivity.db.addItem(item, MainActivity.db.criaNomeTabelaUsuario(MainActivity.usuarioLogado.getId()));
        preencheListaItems(MainActivity.usuarioLogado);
    }

    public static void preencheListaItems(Usuario usuario)
    {
        String NOME_DA_TABELA = MainActivity.db.criaNomeTabelaUsuario(usuario.getId());

        List<Item> items = MainActivity.db.listarTodosItems(NOME_DA_TABELA);

        usuario.setListaDeGastos(items);
    }

    public static void editaItem(Item item)
    {
        MainActivity.db.atualizaItem(item, MainActivity.db.criaNomeTabelaUsuario(MainActivity.usuarioLogado.getId()));
    }

    public static void apagaItem(Item item)
    {
        MainActivity.db.apagarItem(item, MainActivity.db.criaNomeTabelaUsuario(MainActivity.usuarioLogado.getId()));
    }

    public static List<Item> listarItens()
    {
        return MainActivity.db.listarTodosItems(MainActivity.db.criaNomeTabelaUsuario(MainActivity.usuarioLogado.getId()));
    }

    public static Item retornaItem(int index)
    {
        return MainActivity.db.selecionaItem(index, MainActivity.db.criaNomeTabelaUsuario(MainActivity.usuarioLogado.getId()));
    }
}
