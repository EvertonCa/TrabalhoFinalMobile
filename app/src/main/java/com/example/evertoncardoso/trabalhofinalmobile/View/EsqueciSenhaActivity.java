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

public class EsqueciSenhaActivity extends AppCompatActivity {
    EditText edtUser, edtEmail;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_a_senha);
        edtUser = findViewById(R.id.edtUser);
        edtEmail = findViewById(R.id.edtEmail);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mandaEmail(edtEmail.getText().toString(), edtUser.getText().toString())){
                    // alerta foi enviado
                }
                else{
                    // alerta nao foi preechido tudo
                }
            }
        });
    }

    protected boolean mandaEmail(String txtEmail, String txtUsuario)
    {
        Usuario esquecido = null;
        String destinatario, nome, usuario, senha;

        if(txtEmail.isEmpty())
        {
//            esquecido =
        }
        if(txtUsuario.isEmpty())
        {
//            esquecido =
        }
//        if(esquecido == null)
//        {
//            return false;
//        }
        destinatario = "lucaslaheras@hotmail.com";
        nome = "esquecido";
        usuario = "burro";
        senha = "1234";

        final String assunto = "Esqueceu a senha no MaMoney";
        final String texto = "Olá " + nome + "\nUsuario: "+ usuario+"\nSenha: "+ senha;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/pain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{destinatario});
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(Intent.EXTRA_TEXT, texto);
        try{
            startActivity(intent.createChooser(intent, "Enviando email"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

}
