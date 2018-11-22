package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evertoncardoso.trabalhofinalmobile.R;

public class ContaActivity extends AppCompatActivity {
    EditText Valor;
    TextView Remove, Adiciona, Conta, Balanco;
    Button Adicionar, Remover, Confirma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);
        Valor = findViewById(R.id.edtValor);
        Remove = findViewById(R.id.txtRemove);
        Adiciona = findViewById(R.id.txtAdiciona);
        Adicionar = findViewById(R.id.btnAdicionar);
        Remover = findViewById(R.id.btnRemover);
        Confirma = findViewById(R.id.btnConfirma);
        Conta = findViewById(R.id.txtConta);
        Balanco = findViewById(R.id.txtValor);


        Adicionar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Remove.setVisibility(View.INVISIBLE);
                Adiciona.setVisibility(View.VISIBLE);
                Valor.setVisibility(View.VISIBLE);
                Confirma.setVisibility(View.VISIBLE);
            }
        });

        Remover.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Adiciona.setVisibility(View.INVISIBLE);
                Remove.setVisibility(View.VISIBLE);
                Valor.setVisibility(View.VISIBLE);
                Confirma.setVisibility(View.VISIBLE);
            }
        });

        Confirma.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (Remove.getVisibility() == View.VISIBLE){
                    Remove.setVisibility(View.INVISIBLE);
                    Valor.setVisibility(View.INVISIBLE);
                    Confirma.setVisibility(View.INVISIBLE);
                }
                else {
                    Adiciona.setVisibility(View.INVISIBLE);
                    Valor.setVisibility(View.INVISIBLE);
                    Confirma.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
