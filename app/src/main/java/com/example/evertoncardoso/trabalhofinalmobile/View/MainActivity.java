package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;
import com.example.evertoncardoso.trabalhofinalmobile.dao.AES;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    Button button;
    TextView labelEsqueciSenha;

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
        AES cripto = new AES();
        String strLogin = login.getText().toString();
        String strPassword = login.getText().toString();
        String senha = cripto.CriptografaMensagem(strPassword);
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
    public void chamaEsqueciSenha()
    {
        startActivity(new Intent(this, esqueciSenhaActivity.class));
    }

    public String criptografaSenha(String senha){
        int k = 10;
        int cripto;
        String resposta = "";
        String msgOrignal = senha;
        for(int i =0; i < msgOrignal.length(); i++){
            char c = msgOrignal.charAt(i);
            int j = (int) c;
            cripto = j + k;
            resposta += (char)cripto;
        }
        return resposta;
    }
    //comentário zika do role que vai fazer com que eu possa dar push
    public String descriptografaSenha(String cripto){
        int k = 10;
        int original;
        String msgCriptografada = cripto;
        String resposta = "";
        for(int i = 0; i < msgCriptografada.length(); i++){
            char c = msgCriptografada.charAt(i);
            int j = (int)c;
            original = j - k;
            resposta += (char)original;
        }
        return resposta;
    }
}
