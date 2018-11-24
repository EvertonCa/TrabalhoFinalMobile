package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.ItemsController;
import com.example.evertoncardoso.trabalhofinalmobile.Database.DataBase;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Vector;

public class TelaInicialActivity extends AppCompatActivity {
    FloatingActionButton btnAdiciona;
    Button btnPerfil, btnPesquisar, btnSair, btnSemana, btnMes, btnAno, btnGps, btnBolsa, btnDolar;
    private LineGraphSeries<DataPoint> graficoConta;

    public static DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = MainActivity.db;
        Log.d("LOG", "On create");
        setContentView(R.layout.activity_tela_inicial);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        btnSair = findViewById(R.id.btnSair);
        btnAdiciona = findViewById(R.id.btnAdiciona);
        btnSemana = findViewById(R.id.btnSemana);
        btnMes = findViewById(R.id.btnMes);
        btnAno = findViewById(R.id.btnAno);
        btnGps = findViewById(R.id.btnGps);
        btnBolsa = findViewById(R.id.btnBolsa);
        btnDolar = findViewById(R.id.btnDolar);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaPerfil();
            }
        });
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaPesquisa();
            }
        });
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaLogin();
            }
        });
        btnAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaMovimentaConta();
            }
        });
        btnSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graficoSemana();
            }
        });
        btnMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graficoMes();
            }
        });
        btnAno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graficoAno();
            }
        });
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                graficoAno();
            }
        });
        btnBolsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaBolsa();
            }
        });
        btnDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaDolar();
            }
        });

        //ItemsController.criaItem(new Item("Descricao 2", 60.44, "tipo 2", "categoria", 24, 10, 2018));


        graficoSemana();
    }

    public void graficoSemana()
    {
        double vetPontos[] = { 9, 7, 5, 4, 5 }; // vetor com todos os valores da conta no historico
        double vetDatas[] = { 1, 2, 3, 4, 5 }; // vetor com a data de todas as mudanças da conta

        fazGrafico(vetPontos,vetDatas);
    }
    public void graficoMes()
    {
        double vetPontos[] = { 40, 40, 34, 30, 90 }; // vetor com todos os valores da conta no historico
        double vetDatas[] = { 1, 2, 3, 4, 5 }; // vetor com a data de todas as mudanças da conta

        fazGrafico(vetPontos,vetDatas);
    }
    public void graficoAno()
    {
        double vetPontos[] = { 0, 1, 5, 4, 3 }; // vetor com todos os valores da conta no historico
        double vetDatas[] = { 1, 2, 3, 4, 5 }; // vetor com a data de todas as mudanças da conta

        fazGrafico(vetPontos,vetDatas);
    }

    public void fazGrafico(double[] vetPontos, double[] vetDatas)
    {
        GraphView graphView = (GraphView)findViewById(R.id.graph);

        graphView.removeAllSeries();

        graficoConta = new LineGraphSeries<>();


        int qtdPontos = vetPontos.length; // quantas transacoes foram feitas no periodo


        for(int i = 0; i < qtdPontos; i++){
            graficoConta.appendData(new DataPoint(vetDatas[i], vetPontos[i]),true,60);
        }

        graficoConta.setColor(Color.GREEN);
        graficoConta.setThickness(20);
        graphView.addSeries(graficoConta);
    }

    public void chamaLogin()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void chamaMovimentaConta()
    {
        startActivity(new Intent(this, ContaActivity.class));
    }

    public void chamaPerfil()
    {
//        startActivity(new Intent(this, TelaInicialActivity.class));
    }

    public void chamaPesquisa()
    {
        startActivity(new Intent(this, PesquisarActivity.class));
    }

    public void chamaDolar()
    {
        Uri uri = Uri.parse("https://dolarhoje.com/");
        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browser);
    }

    public void chamaBolsa()
    {
        Uri uri = Uri.parse("https://www.infomoney.com.br/mercados/acoes-e-indices");
        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browser);
    }


}
