package com.example.evertoncardoso.trabalhofinalmobile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Implementacao de SQLiteOpenHelper
 *
 * Classe utilitaria para o banco de dados
 *
 */

public class UserDBHandler extends SQLiteOpenHelper {

    private static final String CATEGORIA = "categoria_usuario";

    private String[] scriptSQLCreate;
    private String scriptSQLDelete;

    /**
     * Cria uma instancia de SQLiteHelper
     *
     * @param context
     * @param nomeBanco nome do banco de dados
     * @param versaoBanco versao do banco de dados (se for diferente eh para atualizar)
     * @param scriptSQLCreate SQL com o create table..
     * @param scriptSQLDelete SQL com o drop table...
     */
    UserDBHandler(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete) {
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }

    @Override
    // Criar novo banco...
    public void onCreate(SQLiteDatabase db) {
        Log.i(CATEGORIA, "Criando banco com sql");
        int qtdeScripts = scriptSQLCreate.length;

        // Executa cada sql passado como parametro
        for (int i = 0; i < qtdeScripts; i++) {
            String sql = scriptSQLCreate[i];
            Log.i(CATEGORIA, sql);
            // Cria o banco de dados executando o script de criacao
            db.execSQL(sql);
        }
    }

    @Override
    // Mudou a versao...
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        Log.w(CATEGORIA, "Atualizando da versao " + versaoAntiga + " para " + novaVersao + ". Todos os registros serão deletados.");
        Log.i(CATEGORIA, scriptSQLDelete);
        // Deleta as tabelas...
        db.execSQL(scriptSQLDelete);
        // Cria novamente...
        onCreate(db);
    }
}