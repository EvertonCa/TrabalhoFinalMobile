package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.AlertDialog;

import com.example.evertoncardoso.trabalhofinalmobile.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.Calendar;


public class ContaActivity extends AppCompatActivity {
    EditText Valor, Descricao;
    TextView Remove, Adiciona, Conta, Balanco, Data, txtDescricao, Alimentacao, Casa, Educacao, Imposto, Lazer, Seguro;
    Button Adicionar, Remover, Categoria, Confirma;
    Location location;
    LocationManager locationManager;
    GoogleMap googleMap;
    MapView mapa;
    double latitude, longitude;


    private DatePickerDialog.OnDateSetListener DateSetListener;
    ScrollView ScrollView;
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
        Categoria = findViewById(R.id.btnCategoria);
        ScrollView = findViewById(R.id.scrollview);
        Alimentacao = findViewById(R.id.txtAlimento);
        Casa = findViewById(R.id.txtCasa);
        Educacao = findViewById(R.id.txtEducacao);
        Imposto = findViewById(R.id.txtImposto);
        Lazer = findViewById(R.id.txtLazer);
        Seguro = findViewById(R.id.txtSeguro);
        Confirma = findViewById(R.id.btnConfirma);
        Conta = findViewById(R.id.txtConta);
        Balanco = findViewById(R.id.txtValor);
        Data = findViewById(R.id.txtData);
        txtDescricao = findViewById(R.id.txtDescricao);
        Descricao = findViewById(R.id.edtDescricao);
        mapa = findViewById(R.id.mapa);
        //Balanco.setText(valor no DB);

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            // se nao tem permicao para acessar o GPS
        }
        else{
            locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if(location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        Remover.setText(Double.toString(latitude));
        Adicionar.setText(Double.toString(longitude));
//        System.console().printf(longitude + " " + latitude);




        Adicionar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Valor.setText("");
                Data.setText("");
                Descricao.setText("");
                Remove.setVisibility(View.INVISIBLE);
                Adiciona.setVisibility(View.VISIBLE);
                Valor.setVisibility(View.VISIBLE);
                Categoria.setVisibility(View.VISIBLE);
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
                Categoria.setVisibility(View.VISIBLE);
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
                        Categoria.setVisibility(View.INVISIBLE);
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
                        Categoria.setVisibility(View.INVISIBLE);
                        Data.setVisibility(View.INVISIBLE);
                        Descricao.setVisibility(View.INVISIBLE);
                        txtDescricao.setVisibility(View.INVISIBLE);
                        //Balanco.setText(valor no DB);
                    }
                }
            }
        });

        Categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setVisibility(View.INVISIBLE);
                ScrollView.setVisibility(View.VISIBLE);
            }
        });

        Alimentacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setText("Alimentação");
                ScrollView.setVisibility(View.INVISIBLE);
                Categoria.setVisibility(View.VISIBLE);
            }
        });

        Casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setText("Casa");
                ScrollView.setVisibility(View.INVISIBLE);
                Categoria.setVisibility(View.VISIBLE);
            }
        });

        Educacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setText("Educação");
                ScrollView.setVisibility(View.INVISIBLE);
                Categoria.setVisibility(View.VISIBLE);
            }
        });

        Imposto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setText("Imposto");
                ScrollView.setVisibility(View.INVISIBLE);
                Categoria.setVisibility(View.VISIBLE);
            }
        });

        Lazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setText("Lazer");
                ScrollView.setVisibility(View.INVISIBLE);
                Categoria.setVisibility(View.VISIBLE);
            }
        });

        Seguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria.setText("Seguro");
                ScrollView.setVisibility(View.INVISIBLE);
                Categoria.setVisibility(View.VISIBLE);
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