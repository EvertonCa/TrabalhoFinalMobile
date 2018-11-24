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

import com.example.evertoncardoso.trabalhofinalmobile.R;



public class PerfilConfigActivity extends AppCompatActivity {
    private TextView Usuario, Nome,txtSenhaAntiga, txtSenhaNova;
    private EditText Telefone, Email, SenhaAntiga, SenhaNova;
    private Button Salva, AlteraSenha, Cancela;
    private ImageButton Imagem, Icone;
    private String Caminho;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilconfig);
        Usuario = findViewById(R.id.txtUsuario);
        Nome = findViewById(R.id.txtNome);
        txtSenhaAntiga = findViewById(R.id.txtSenha);
        txtSenhaNova = findViewById(R.id.txtSenhaNova);
        Telefone = findViewById(R.id.edtTelefone);
        Email = findViewById(R.id.edtEmail);
        SenhaAntiga = findViewById(R.id.edtSenha);
        SenhaNova = findViewById(R.id.edtSenhaNova);
        Salva = findViewById(R.id.btnConfirm);
        AlteraSenha = findViewById(R.id.btnSenha);
        Cancela = findViewById(R.id.btnCancela);
        Icone = findViewById(R.id.imgbtnCamera);
        Imagem = findViewById(R.id.imgUsuario);
        /*
        Imagem.setImage(DB);
        Usuario.setText(DB);
        Nome.setText(DB);
        Telefone.setText(DB);
        Email.setText(DB);
         */

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

        Salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cancela.getVisibility() == View.VISIBLE){
                    if (Telefone.getText().toString().equals("") || Email.getText().toString().equals("") ||
                            SenhaAntiga.getText().toString().equals("") || SenhaNova.getText().toString().equals("")){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilConfigActivity.this);
                        dlgAlert.setMessage("Campos Não podem estar vazios ou nenhuma alteração será realizada, Caso não deseje modificar a senha clique em Cancelar");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    else{
                        /*if (SenhaAntiga.getText().toString().equals(BD)){
                            bdTelefone = Telefone.getText().toString();
                            bdEmail = Email.getText().toString();
                            bdSenha = SenhaNova.getText().toString();
                            dbImage = Image.getImage();
                            txtSenhaAntiga.setVisibility(View.INVISIBLE);
                            txtSenhaNova.setVisibility(View.INVISIBLE);
                            SenhaAntiga.setVisibility(View.INVISIBLE);
                            SenhaNova.setVisibility(View.INVISIBLE);
                            Cancela.setVisibility(View.INVISIBLE);
                            AlteraSenha.setVisibility(View.VISIBLE);
                        }
                        else {
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilConfigActivity.this);
                            dlgAlert.setMessage("Senha Atual Incorreta!");
                            dlgAlert.setTitle("Erro");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }
                        */
                        SenhaAntiga.setText("");
                        SenhaNova.setText("");
                    }
                }
                else {
                    if (Telefone.getText().toString().equals("") || Email.getText().toString().equals("")){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilConfigActivity.this);
                        dlgAlert.setMessage("Campos Não podem estar vazios ou nenhuma alteração será realizada");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    else {
                        /*
                        bdTelefone = Telefone.getText().toString();
                        bdEmail = Email.getText().toString();
                        dbImage = Image.getImage();
                         */
                    }
                }
            }
        });

        AlteraSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlteraSenha.setVisibility(View.INVISIBLE);
                txtSenhaAntiga.setVisibility(View.VISIBLE);
                txtSenhaNova.setVisibility(View.VISIBLE);
                SenhaAntiga.setVisibility(View.VISIBLE);
                SenhaNova.setVisibility(View.VISIBLE);
                Cancela.setVisibility(View.VISIBLE);
            }
        });

        Cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSenhaAntiga.setVisibility(View.INVISIBLE);
                txtSenhaNova.setVisibility(View.INVISIBLE);
                SenhaAntiga.setVisibility(View.INVISIBLE);
                SenhaNova.setVisibility(View.INVISIBLE);
                Cancela.setVisibility(View.INVISIBLE);
                AlteraSenha.setVisibility(View.VISIBLE);
            }
        });

        Imagem.setOnClickListener(new View.OnClickListener() {
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
            Caminho = picturePath;
            Imagem.setImageBitmap(imagemGaleria);
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
