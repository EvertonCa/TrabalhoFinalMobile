package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.AES;
import com.example.evertoncardoso.trabalhofinalmobile.Controller.UsersController;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;

import java.util.List;

public class RecuperaSenhaActivity extends AppCompatActivity {

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
                    Toast.makeText(RecuperaSenhaActivity.this, "Email enviado!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(RecuperaSenhaActivity.this, "Email NÃO enviado!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected boolean mandaEmail(String txtEmail, String txtUsuario)
    {
        Usuario esquecido = null;
        List<Usuario> listaUsuarios = UsersController.retornaListaUsuarios();

        for(Usuario temp:listaUsuarios)
        {
            if(temp.getLogin().equals(txtUsuario) || temp.getEmail().equals(txtEmail))
            {
                esquecido = temp;
            }
        }

        if(esquecido == null)
            return false;

        final String assunto = "Esqueceu a senha no MaMoney";
        final String texto = "Olá " + esquecido.getNome() + "\nUsuario: "+ esquecido.getLogin() + "\nSenha: "+ AES.descriptografaSenha(esquecido.getPassword());

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/pain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{esquecido.getEmail()});
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
