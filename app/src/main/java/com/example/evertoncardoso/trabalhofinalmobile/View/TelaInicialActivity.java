package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Usuario;
import com.example.evertoncardoso.trabalhofinalmobile.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Vector;

public class TelaInicialActivity extends AppCompatActivity {
    FloatingActionButton btnAdiciona;
    Button btnPerfil, btnPesquisar, btnSair, btnSemana, btnMes, btnAno;
    private LineGraphSeries<DataPoint> graficoConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        btnSair = findViewById(R.id.btnSair);
        btnAdiciona = findViewById(R.id.btnAdiciona);
        btnSemana = findViewById(R.id.btnSemana);
        btnMes = findViewById(R.id.btnMes);
        btnAno = findViewById(R.id.btnAno);

        GraphView graphView = (GraphView)findViewById(R.id.graph);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaPerfil();
            }
        });
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaPesquisar();
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
        graphView.addSeries(graficoConta);
    }

    public void chamaLogin()
    {
        startActivity(new Intent(this, MainActivity.class));
    }
    public void chamaMovimentaConta()
    {
//        startActivity(new Intent(this, TelaInicialActivity.class));
    }
    public void chamaPerfil()
    {
//        startActivity(new Intent(this, TelaInicialActivity.class));
    }
    public void chamaPesquisar()
    {
//        startActivity(new Intent(this, TelaInicialActivity.class));
    }


}
