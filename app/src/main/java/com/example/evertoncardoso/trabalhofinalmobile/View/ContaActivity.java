package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;

import com.example.evertoncardoso.trabalhofinalmobile.R;


import java.util.Calendar;


public class ContaActivity extends AppCompatActivity {
    EditText Valor, Descricao;
    TextView Remove, Adiciona, Conta, Balanco, Data, txtDescricao;
    Button Adicionar, Remover, Confirma;
    private DatePickerDialog.OnDateSetListener DateSetListener;
    private static final String TAG = "Data";


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
        Data = findViewById(R.id.txtData);
        txtDescricao = findViewById(R.id.txtDescricao);
        Descricao = findViewById(R.id.edtDescricao);
        //Balanco.setText(valor no DB);



        Adicionar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Valor.setText("");
                Data.setText("");
                Descricao.setText("");
                Remove.setVisibility(View.INVISIBLE);
                Adiciona.setVisibility(View.VISIBLE);
                Valor.setVisibility(View.VISIBLE);
                Confirma.setVisibility(View.VISIBLE);
                Data.setVisibility(View.VISIBLE);
                Descricao.setVisibility(View.VISIBLE);
                txtDescricao.setVisibility(View.VISIBLE);
                Valor.setTextColor(getResources().getColor(R.color.green));
            }
        });

        Remover.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Valor.setText("");
                Data.setText("");
                Descricao.setText("");
                Adiciona.setVisibility(View.INVISIBLE);
                Remove.setVisibility(View.VISIBLE);
                Valor.setVisibility(View.VISIBLE);
                Confirma.setVisibility(View.VISIBLE);
                Data.setVisibility(View.VISIBLE);
                Descricao.setVisibility(View.VISIBLE);
                txtDescricao.setVisibility(View.VISIBLE);
                Valor.setTextColor(getResources().getColor(R.color.red));
            }
        });


        Confirma.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (Remove.getVisibility() == View.VISIBLE){
                    if (Valor.getText().toString().equals("") || Data.getText().toString().equals("") || Descricao.getText().toString().equals("")){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ContaActivity.this);
                        dlgAlert.setMessage("Campos obrigatorios não preenchidos");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    else{
                        //manda pro banco as infos
                        Valor.setText("");
                        Data.setText("");
                        Descricao.setText("");
                        Remove.setVisibility(View.INVISIBLE);
                        Valor.setVisibility(View.INVISIBLE);
                        Confirma.setVisibility(View.INVISIBLE);
                        Data.setVisibility(View.INVISIBLE);
                        Descricao.setVisibility(View.INVISIBLE);
                        txtDescricao.setVisibility(View.INVISIBLE);
                        //Balanco.setText(valor no DB);
                    }
                }
                else {
                    if (Valor.getText().toString().equals("") || Data.getText().toString().equals("") || Descricao.getText().toString().equals("")){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ContaActivity.this);
                        dlgAlert.setMessage("Campos obrigatorios não preenchidos");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    else{
                        //manda pro banco as infos
                        Valor.setText("");
                        Data.setText("");
                        Descricao.setText("");
                        Adiciona.setVisibility(View.INVISIBLE);
                        Valor.setVisibility(View.INVISIBLE);
                        Confirma.setVisibility(View.INVISIBLE);
                        Data.setVisibility(View.INVISIBLE);
                        Descricao.setVisibility(View.INVISIBLE);
                        txtDescricao.setVisibility(View.INVISIBLE);
                        //Balanco.setText(valor no DB);
                    }
                }
            }
        });

        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ContaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DateSetListener, ano, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy " + dayOfMonth + "/" + month + "/" + year);

                String date = dayOfMonth + "/" + month + "/" + year;
                Data.setText(date);
            }
        };

        }
}
