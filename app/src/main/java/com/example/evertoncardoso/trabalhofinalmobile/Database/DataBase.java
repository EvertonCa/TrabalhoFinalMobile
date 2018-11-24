package com.example.evertoncardoso.trabalhofinalmobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String BANCO_MAMONEY = "bd_mamoney";

    /* Strings para tabela de usuarios */

    public static final String TABELA_USUARIO = "tb_usuario";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_LOGIN = "login";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_FOTO = "foto";

    /* Strings para tabela de itens */

    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_tipo = "tipo";
    public static final String COLUNA_categoria = "categoria";
    public static final String COLUNA_dia = "dia";
    public static final String COLUNA_mes = "mes";
    public static final String COLUNA_ano = "ano";

    public DataBase(Context context) {
        super(context, BANCO_MAMONEY, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_USUARIO + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, "
                + COLUNA_LOGIN + " TEXT, "
                + COLUNA_SENHA + " TEXT, "
                + COLUNA_NOME + " TEXT, "
                + COLUNA_TELEFONE + " TEXT, "
                + COLUNA_EMAIL + " TEXT, "
                + COLUNA_FOTO + " TEXT)";

        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void criaNovaTabela(String NOME_TABELA_NOVA)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String QUERY_COLUNA = "CREATE TABLE " + NOME_TABELA_NOVA + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, "
                + COLUNA_DESCRICAO + " TEXT, "
                + COLUNA_VALOR + " TEXT, "
                + COLUNA_tipo + " TEXT, "
                + COLUNA_categoria + " TEXT, "
                + COLUNA_dia + " TEXT, "
                + COLUNA_mes + " TEXT, "
                + COLUNA_ano + " TEXT)";

        db.execSQL(QUERY_COLUNA);
    }

    /* CRUD ABAIXO */

    public void addUsuario(Usuario usuario)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getPassword());
        values.put(COLUNA_NOME, usuario.getNome());
        values.put(COLUNA_TELEFONE, usuario.getTelefone());
        values.put(COLUNA_EMAIL, usuario.getEmail());
        values.put(COLUNA_FOTO, usuario.getEnderecoFotos());

        db.insert(TABELA_USUARIO, null, values);
        db.close();

        List<Usuario> lista = listarTodosUsuarios();

        int id = 0;

        for(Usuario temp: lista)
        {
            if(temp.getLogin().equals(usuario.getLogin()) && temp.getPassword().equals(usuario.getPassword()))
            {
                id = temp.getId();
                break;
            }
        }

        String NOME_DA_TABELA = criaNomeTabelaUsuario(id);
        criaNovaTabela(NOME_DA_TABELA);
    }

    public String criaNomeTabelaUsuario(int id)
    {
        String NOME_DA_TABELA = "td_usuario_" + String.valueOf(id);

        return NOME_DA_TABELA;
    }

    public void apagarUsuario(Usuario usuario)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_USUARIO, COLUNA_ID + " = ?", new String[] { String.valueOf(usuario.getId())});

        db.close();
    }

    public Usuario selecionarUsuario(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_ID,COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME, COLUNA_TELEFONE, COLUNA_EMAIL, COLUNA_FOTO},
                COLUNA_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        Usuario usuario = new Usuario(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));

        return usuario;
    }

    public void atualizaUsuario(Usuario usuario)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getPassword());
        values.put(COLUNA_NOME, usuario.getNome());
        values.put(COLUNA_TELEFONE, usuario.getTelefone());
        values.put(COLUNA_EMAIL, usuario.getEmail());
        values.put(COLUNA_FOTO, usuario.getEnderecoFotos());

        db.update(TABELA_USUARIO, values, COLUNA_ID + " = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public List<Usuario> listarTodosUsuarios()
    {
        List<Usuario> lista = new ArrayList<Usuario>();

        String QUERY = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);

        if(cursor.moveToFirst())
        {
            do {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(cursor.getString(0)));
                usuario.setLogin(cursor.getString(1));
                usuario.setPassword(cursor.getString(2));
                usuario.setNome(cursor.getString(3));
                usuario.setTelefone(cursor.getString(4));
                usuario.setEmail(cursor.getString(5));
                usuario.setEnderecoFotos(cursor.getString(6));

                lista.add(usuario);

            } while (cursor.moveToNext());
        }

        return lista;
    }

    public void addItem(Item item, String TABELA)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_DESCRICAO, item.getDescricao());
        values.put(COLUNA_VALOR, String.valueOf(item.getValor()));
        values.put(COLUNA_tipo, item.getTipo());
        values.put(COLUNA_categoria, item.getCategoria());
        values.put(COLUNA_dia, String.valueOf(item.getDia()));
        values.put(COLUNA_mes, String.valueOf(item.getMes()));
        values.put(COLUNA_ano, String.valueOf(item.getAno()));

        db.insert(TABELA, null, values);
        db.close();
    }

    public void apagarItem(Item item, String TABELA)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA, COLUNA_ID + " = ?", new String[] { String.valueOf(item.getId())});

        db.close();
    }

    public Item selecionaItem(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_ID, COLUNA_DESCRICAO, COLUNA_VALOR, COLUNA_tipo, COLUNA_categoria, COLUNA_dia, COLUNA_mes, COLUNA_ano},
                COLUNA_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        Item item = new Item(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Double.parseDouble(cursor.getString(2)),
                cursor.getString(3),
                cursor.getString(4),
                Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)));

        return item;
    }

    public void atualizaItem(Item item, String TABELA)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_DESCRICAO, item.getDescricao());
        values.put(COLUNA_VALOR, String.valueOf(item.getValor()));
        values.put(COLUNA_tipo, item.getTipo());
        values.put(COLUNA_categoria, item.getCategoria());
        values.put(COLUNA_dia, String.valueOf(item.getDia()));
        values.put(COLUNA_mes, String.valueOf(item.getMes()));
        values.put(COLUNA_ano, String.valueOf(item.getAno()));

        db.update(TABELA, values, COLUNA_ID + " = ?", new String[]{String.valueOf(item.getId())});
    }

    public List<Item> listarTodosItems(String TABELA)
    {
        List<Item> items = new ArrayList<Item>();

        String QUERY = "SELECT * FROM " + TABELA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);

        if(cursor.moveToFirst())
        {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setDescricao(cursor.getString(1));
                item.setValor(Double.parseDouble(cursor.getString(2)));
                item.setTipo(cursor.getString(3));
                item.setCategoria(cursor.getString(4));
                item.setDia(Integer.parseInt(cursor.getString(5)));
                item.setMes(Integer.parseInt(cursor.getString(6)));
                item.setAno(Integer.parseInt(cursor.getString(7)));

                items.add(item);

            } while (cursor.moveToNext());
        }

        return items;
    }
}
