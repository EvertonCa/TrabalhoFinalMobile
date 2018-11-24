package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.AES;
import com.example.evertoncardoso.trabalhofinalmobile.Controller.ItemsController;
import com.example.evertoncardoso.trabalhofinalmobile.Controller.LogInController;
import com.example.evertoncardoso.trabalhofinalmobile.Database.DataBase;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    TextView labelEsqueciSenha;

    public static Usuario usuarioLogado;

    public static DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(this);
        setContentView(R.layout.activity_main);
        labelEsqueciSenha = findViewById(R.id.labelEsqueciSenha);

        labelEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaEsqueciSenha();
            }
        });

        //db.addUsuario(new Usuario("admin", AES.criptografaSenha("senhaAdmin"), "Nome", "Telefone", "email", "endereco/fotos"));

        //Toast.makeText(MainActivity.this, "Salvo com Sucesso!", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        Log.d("LOG", "On pause");
        super.onPause();
    }

    public void verificaLogin(View view)
    {
        login = findViewById(R.id.campoUsuario);
        password = findViewById(R.id.campoSenha);

        String strLogin = login.getText().toString();
        String strPassword = password.getText().toString();

        String senhaCripto = AES.criptografaSenha(strPassword);
        String volta = AES.descriptografaSenha(senhaCripto);

        Log.d("LOG", "Senha sem cripto: " + volta + " Senha criptografada: " + senhaCripto);

        Usuario usuario = LogInController.verificaLogIn(strLogin, senhaCripto);

        if(usuario == null)
        {
            Toast.makeText(MainActivity.this, "Usuário ou Senha Incorreto(s)!", Toast.LENGTH_LONG).show();
            login.setText("");
            password.setText("");
        }
        else
        {
            usuarioLogado = usuario;
            ItemsController.preencheListaItems(usuarioLogado);
            chamaMenuPrincipal();
        }
    }

    public void chamaMenuPrincipal()
    {
        startActivity(new Intent(this, TelaInicialActivity.class));
    }

    public void chamaEsqueciSenha()
    {
        startActivity(new Intent(this, esqueciSenhaActivity.class));
    }

    public void chamaCriaUser(View view)
    {
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
