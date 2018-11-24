package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.AES;
import com.example.evertoncardoso.trabalhofinalmobile.Controller.UsersController;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText campoUsuario, campoNome, campoTelefone, campoEmail, campoSenha, campoSenhaConfim;
    private Button btnCadastra;
    private ImageButton btnImagem;
    private String strCaminho = "";
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        campoUsuario = findViewById(R.id.edtUsuario);
        campoNome = findViewById(R.id.edtNome);
        campoTelefone = findViewById(R.id.edtTelefone);
        campoEmail = findViewById(R.id.edtEmail);
        campoSenha = findViewById(R.id.edtSenha);
        campoSenhaConfim = findViewById(R.id.edtSenhaConfirm);
        btnCadastra = findViewById(R.id.btnCadastra);
        btnImagem = findViewById(R.id.imgUsuario);

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

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campoNome.getText().toString().equals("") || campoTelefone.getText().toString().equals("") || campoEmail.getText().toString().equals("") ||
                        campoUsuario.getText().toString().equals("") || campoSenha.getText().toString().equals("") || campoSenhaConfim.getText().toString().equals("")){
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(CadastroActivity.this);
                    dlgAlert.setMessage("Campos Não podem estar vazios ou nenhuma alteração será realizada");
                    dlgAlert.setTitle("Erro");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                else{
                    //manda pro db
                    Usuario user = new Usuario(campoUsuario.getText().toString(), AES.criptografaSenha(campoSenha.getText().toString()),
                            campoNome.getText().toString(), campoTelefone.getText().toString(), campoEmail.getText().toString(),
                            strCaminho);
                    UsersController.criaUsuario(user);
                    Toast.makeText(CadastroActivity.this, "Usuário criado com sucesso!", Toast.LENGTH_LONG).show();

                    chamaLogin();
                }
            }
        });

        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
            strCaminho = picturePath;
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            btnImagem.setImageBitmap(imagemGaleria);
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

    public void chamaLogin()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

}
