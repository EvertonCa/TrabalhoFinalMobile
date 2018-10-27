package com.example.evertoncardoso.trabalhofinalmobile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CriacaoBancoUsuarioScript {

    // Script para fazer drop na tabela
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS autor";

    protected SQLiteDatabase db;

    // Cria a tabela com o "_id" sequencial
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
            "create table autor ( _id integer primary key autoincrement, login text not null, password text not null, nome text not null, telefone text not null);",
            "insert into autor(login,password,nome,telefone) values('eve.023@hotmail.com','Senha','Everton','999999999');"};

    // Nome do banco
    private static final String NOME_BANCO = "bdusuarios";

    // Controle de versão
    private static final int VERSAO_BANCO = 4;

    // Nome da tabela
    public static final String TABELA_USUARIO = "usuario";

    // Classe utilitária para abrir, criar, e atualizar o banco de dados
    private UserDBHandler dbHandler;

    // Cria o banco de dados com um script SQL
    public CriacaoBancoUsuarioScript(Context ctx) {
        // Criar utilizando um script SQL
        dbHandler = new UserDBHandler(ctx, CriacaoBancoUsuarioScript.NOME_BANCO, CriacaoBancoUsuarioScript.VERSAO_BANCO,
                CriacaoBancoUsuarioScript.SCRIPT_DATABASE_CREATE, CriacaoBancoUsuarioScript.SCRIPT_DATABASE_DELETE);

        // abre o banco no modo escrita
        db = dbHandler.getWritableDatabase();
    }

    // Fecha o banco
    public void fechar() {
        if (db != null) {
            db.close();
        }
        if (dbHandler != null) {
            dbHandler.close();
        }
    }
}

