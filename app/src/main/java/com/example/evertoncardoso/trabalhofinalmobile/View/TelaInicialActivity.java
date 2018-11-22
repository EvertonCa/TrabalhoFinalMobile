package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.support.design.widget.FloatingActionButton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;

public class TelaInicialActivity extends AppCompatActivity {
    FloatingActionButton adicionarTransacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }


}
