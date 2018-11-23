package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaMenuPrincipal();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Cancela para nao ficar nada pendente na tela
        setResult(RESULT_CANCELED);

        // Fecha a tela
        finish();
    }

    public void verificaLogin(View view)
    {
        login = findViewById(R.id.campoUsuario);
        password = findViewById(R.id.campoSenha);

        String strLogin = login.getText().toString();
        String strPassword = login.getText().toString();

        Usuario usuario;

        if(CadastrarActivity.usuarioDAO.buscarUsuarioPorLogin(strLogin) != null)
        {
            usuario = CadastrarActivity.usuarioDAO.buscarUsuarioPorLogin(strLogin);

            if(usuario != null)
            {
                if(strPassword.equals(usuario.getPassword()))
                {
                    chamaMenuPrincipal();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Senha INCORRETA", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "Login NÃO ENCONTRADO", Toast.LENGTH_SHORT).show();
        }
    }

    public void chamaMenuPrincipal()
    {
        startActivity(new Intent(this, TelaInicialActivity.class));
    }
}
