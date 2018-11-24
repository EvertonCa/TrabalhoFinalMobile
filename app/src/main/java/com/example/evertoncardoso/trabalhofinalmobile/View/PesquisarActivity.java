package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.ItemsController;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.R;

import java.util.ArrayList;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity {

    CalendarView calendarViewInicial, calendarViewFinal;
    int diaInicial, diaFinal, mesInicial, mesFinal, anoInicial, anoFinal;
    public static String data;
    public static List<Item> listaPeneirada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        calendarViewInicial = findViewById(R.id.calendarViewInicial);
        calendarViewFinal = findViewById(R.id.calendarViewFinal);

        calendarViewInicial.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaInicial = dayOfMonth;
                mesInicial = month;
                anoInicial = year;
            }
        });

        calendarViewFinal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaFinal = dayOfMonth;
                mesFinal = month;
                anoFinal = year;
            }
        });
    }

    public void pesquisarItens(View view)
    {
        List<Item> listaCompleta = ItemsController.listarItens();
        listaPeneirada = new ArrayList<Item>();

        for(Item i: listaCompleta)
        {
            if(i.getAno() >= anoInicial && i.getAno() <= anoFinal)
            {
                if(i.getMes() >= mesInicial && i.getMes() <= mesFinal)
                {
                    if(i.getDia() >= diaInicial && i.getDia() <= diaFinal)
                    {
                        listaPeneirada.add(i);
                    }
                }
            }
        }

        data = diaInicial + "/" + mesInicial + "/" + anoInicial + " até " + diaFinal
                + "/" + mesFinal + "/" + anoFinal;

        startActivity(new Intent(this, ResultadosActivity.class));
    }
}
