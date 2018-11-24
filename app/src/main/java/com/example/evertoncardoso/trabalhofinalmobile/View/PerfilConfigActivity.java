package com.example.evertoncardoso.trabalhofinalmobile.View;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.AES;
import com.example.evertoncardoso.trabalhofinalmobile.Controller.UsersController;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;



public class PerfilConfigActivity extends AppCompatActivity {
    private TextView txtSenhaAntiga, txtSenhaNova;
    private EditText campoUsuario, campoNome, campoTelefone, campoEmail, campoSenhaAntiga, campoSenhaNova;
    private Button btnSalva, btnAlteraSenha, btnCancela;
    private ImageButton imgbtnImagem;
    private String strCaminho;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    public Usuario user = MainActivity.usuarioLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilconfig);
        campoUsuario = findViewById(R.id.edtUsuario);
        campoNome = findViewById(R.id.edtNome);
        txtSenhaAntiga = findViewById(R.id.txtSenha);
        txtSenhaNova = findViewById(R.id.txtSenhaNova);
        campoTelefone = findViewById(R.id.edtTelefone);
        campoEmail = findViewById(R.id.edtEmail);
        campoSenhaAntiga = findViewById(R.id.edtSenha);
        campoSenhaNova = findViewById(R.id.edtSenhaNova);
        btnSalva = findViewById(R.id.btnConfirm);
        btnAlteraSenha = findViewById(R.id.btnSenha);
        btnCancela = findViewById(R.id.btnCancela);
        imgbtnImagem = findViewById(R.id.imgUsuario);
        campoUsuario.setText(user.getLogin());
        campoNome.setText(user.getNome());
        campoTelefone.setText(user.getTelefone());
        campoEmail.setText(user.getEmail());
        if (!user.getEnderecoFotos().equals("")){
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(user.getEnderecoFotos()));
            imgbtnImagem.setImageBitmap(imagemGaleria);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }

        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCancela.getVisibility() == View.VISIBLE){
                    if (campoTelefone.getText().toString().equals("") || campoEmail.getText().toString().equals("") ||
                            campoSenhaAntiga.getText().toString().equals("") || campoSenhaNova.getText().toString().equals("")){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilConfigActivity.this);
                        dlgAlert.setMessage("Campos Não podem estar vazios ou nenhuma alteração será realizada, Caso não deseje modificar a senha clique em Cancelar");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    else{
                        if (AES.criptografaSenha(campoSenhaAntiga.getText().toString()).equals(user.getPassword())){
                            user.setEnderecoFotos(strCaminho);
                            user.setNome(campoNome.getText().toString());
                            user.setLogin(campoUsuario.getText().toString());
                            user.setEmail(campoEmail.getText().toString());
                            user.setTelefone(campoTelefone.getText().toString());
                            user.setPassword(AES.criptografaSenha(campoSenhaNova.getText().toString()));
                            UsersController.editaUsuario(user);
                            Toast.makeText(PerfilConfigActivity.this, "Usuário editado com sucesso!", Toast.LENGTH_LONG).show();

                            txtSenhaAntiga.setVisibility(View.INVISIBLE);
                            txtSenhaNova.setVisibility(View.INVISIBLE);
                            campoSenhaAntiga.setVisibility(View.INVISIBLE);
                            campoSenhaNova.setVisibility(View.INVISIBLE);
                            btnCancela.setVisibility(View.INVISIBLE);
                            btnAlteraSenha.setVisibility(View.VISIBLE);
                        }
                        else {
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilConfigActivity.this);
                            dlgAlert.setMessage("Senha Atual Incorreta!");
                            dlgAlert.setTitle("Erro");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }
                        campoSenhaAntiga.setText("");
                        campoSenhaNova.setText("");
                    }
                }
                else {
                    if (campoTelefone.getText().toString().equals("") || campoEmail.getText().toString().equals("")){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilConfigActivity.this);
                        dlgAlert.setMessage("Campos Não podem estar vazios ou nenhuma alteração será realizada");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    else {
                        Usuario user = new Usuario(campoUsuario.getText().toString(), AES.criptografaSenha(campoSenhaNova.getText().toString()),
                                campoNome.getText().toString(), campoTelefone.getText().toString(), campoEmail.getText().toString(),
                                strCaminho);
                        UsersController.editaUsuario(user);
                        Toast.makeText(PerfilConfigActivity.this, "Usuário editado com sucesso!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnAlteraSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAlteraSenha.setVisibility(View.INVISIBLE);
                txtSenhaAntiga.setVisibility(View.VISIBLE);
                txtSenhaNova.setVisibility(View.VISIBLE);
                campoSenhaAntiga.setVisibility(View.VISIBLE);
                campoSenhaNova.setVisibility(View.VISIBLE);
                btnCancela.setVisibility(View.VISIBLE);
            }
        });

        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSenhaAntiga.setVisibility(View.INVISIBLE);
                txtSenhaNova.setVisibility(View.INVISIBLE);
                campoSenhaAntiga.setVisibility(View.INVISIBLE);
                campoSenhaNova.setVisibility(View.INVISIBLE);
                btnCancela.setVisibility(View.INVISIBLE);
                btnAlteraSenha.setVisibility(View.VISIBLE);
            }
        });

        imgbtnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGENS);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS){
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            strCaminho = picturePath;
            imgbtnImagem.setImageBitmap(imagemGaleria);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        if (requestCode == PERMISSAO_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permissão concedida.
            } else {
                //permissão não concedida.
            }
            return;
        }
    }
}
