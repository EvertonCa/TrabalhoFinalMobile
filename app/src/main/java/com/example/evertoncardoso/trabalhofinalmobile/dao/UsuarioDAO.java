package com.example.evertoncardoso.trabalhofinalmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;

public class UsuarioDAO {

    private static final String CATEGORIA = "categoria_usuario";

    // Nome do banco
    private static final String NOME_BANCO = "bdusuarios";



    protected SQLiteDatabase db;

    public UsuarioDAO(Context ctx) {
        // Abre o banco de dados ja existente
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
    }

    // Salva o autor, insere um novo ou atualiza
    public long salvar(Usuario autor) {
        long id = autor.get_id();

        ContentValues values = new ContentValues();
        values.put("login", autor.getLogin());
        values.put("password", autor.getPassword());
        values.put("nome", autor.getNome());
        values.put("telefone", autor.getTelefone());

        if (id != 0) {
            String _id = String.valueOf(autor.get_id());

            String where = "_id" + "=?";
            String[] whereArgs = new String[] { _id };

            int count = db.update("usuario", values, where, whereArgs);
        } else {
            // Insere novo
            id = db.insert("usuario", "", values);
        }

        return id;
    }


    // Deleta o autor com o id fornecido
    public int apagar(long id) {
        String where = "_id" + "=?";

        String _id = String.valueOf(id);
        String[] whereArgs = new String[] { _id };

        int count = db.delete("usuario", where, whereArgs);
        Log.i(CATEGORIA, "Deletou [" + count + "] registros");


        return count;
    }


    // Retorna uma lista com todos os autores
    public List<Usuario> listarUsuarios() {
        Cursor c = db.query("usuario", new String[]{"_id", "login", "password", "nome", "telefone"}, null, null, null, null, null, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();

        if (c.moveToFirst()) {

            // Recupera os indices das colunas
            int idxId = c.getColumnIndex("_id");
            int idxLogin = c.getColumnIndex("login");
            int idxPassword = c.getColumnIndex("password");
            int idxNome = c.getColumnIndex("nome");
            int idxTelefone = c.getColumnIndex("telefone");


            do {
                Usuario pessoa = new Usuario();


                // recupera os atributos de autor
                pessoa.set_id(c.getLong(idxId));
                pessoa.setLogin(c.getString(idxLogin));
                pessoa.setPassword(c.getString(idxPassword));
                pessoa.setNome(c.getString(idxNome));
                pessoa.setTelefone(c.getString(idxTelefone));
                usuarios.add(pessoa);


            } while (c.moveToNext());
        }

        return usuarios;
    }


    // Busca o autor pelo id
    public Usuario buscarUsuario(long id) {
        // select * from autor where _id=?
        Cursor c = db.query(true, "usuario",  new String[] { "_id", "login", "password", "nome", "telefone" },  "_id" + "=" + id, null, null, null, null, null);

        if (c.getCount() > 0) {

            // Posiciona no primeiro elemento do cursor
            c.moveToFirst();

            Usuario pessoa = new Usuario();

            // Le os dados
            pessoa.set_id(c.getLong(0));
            pessoa.setLogin(c.getString(1));
            pessoa.setPassword(c.getString(2));
            pessoa.setNome(c.getString(3));
            pessoa.setTelefone(c.getString(4));

            return pessoa;
        }

        return null;
    }


    // Busca o autor pelo nome "select * from autor where login=?"
    public Usuario buscarUsuarioPorLogin(String login) {
        Usuario pessoa = null;

        try {
            // Idem a: SELECT _id,nome,placa,ano from autor where nome = ?
            Cursor c = db.query("usuario", new String[] { "_id", "login", "password", "nome", "telefone" }, "login" + "='" + login + "'", null, null, null, null);


            if (c.moveToNext()) {

                pessoa = new Usuario();

                // utiliza os métodos getLong(), getString(), getInt(), etc para recuperar os valores
                pessoa.set_id(c.getLong(0));
                pessoa.setLogin(c.getString(1));
                pessoa.setPassword(c.getString(2));
                pessoa.setNome(c.getString(3));
                pessoa.setTelefone(c.getString(4));
            }
        } catch (SQLException e) {
            Log.e(CATEGORIA, "Erro ao buscar o usuario pelo login: " + e.toString());
            return null;
        }

        return pessoa;
    }



    // Fecha o banco
    public void fechar() {
        // fecha o banco de dados
        if (db != null) {
            db.close();
        }
    }
}
