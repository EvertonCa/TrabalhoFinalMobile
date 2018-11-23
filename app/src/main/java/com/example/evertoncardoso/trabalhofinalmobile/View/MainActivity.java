package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.LogInController;
import com.example.evertoncardoso.trabalhofinalmobile.Database.DataBase;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    Button button;
    TextView labelEsqueciSenha;

    public static Usuario usuarioLogado;

    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        labelEsqueciSenha = findViewById(R.id.labelEsqueciSenha);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaMenuPrincipal();
            }
        });
        labelEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaEsqueciSenha();
            }
        });


    }

        //db.addUsuario(new Usuario("admin", "senhaAdmin", "Nome", "Telefone", "email", "endereco/fotos"));

        //Toast.makeText(MainActivity.this, "Salvo com Sucesso!", Toast.LENGTH_LONG).show();
    }

    public void verificaLogin(View view)
    {
        login = findViewById(R.id.campoUsuario);
        password = findViewById(R.id.campoSenha);

        String strLogin = login.getText().toString();
        String strPassword = password.getText().toString();

        Usuario usuario = LogInController.verificaLogIn(db, strLogin, strPassword);

        if(usuario == null)
        {
            Toast.makeText(MainActivity.this, "Usuário ou Senha Incorreto(s)!", Toast.LENGTH_LONG).show();
            login.setText("");
            password.setText("");
        }
        else
        {
            usuarioLogado = usuario;
            LogInController.preencheListaItems(db, usuarioLogado);
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
}
