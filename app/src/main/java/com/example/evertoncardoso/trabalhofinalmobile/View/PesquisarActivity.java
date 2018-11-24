package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CalendarView;
import android.view.View;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.R;

public class PesquisarActivity extends AppCompatActivity {

    CalendarView calendarViewInicial, calendarViewFinal;
    int diaInicial, diaFinal, mesInicial, mesFinal, anoInicial, anoFinal;
    Button btnIr;

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

    public void botaoClicado(View view)
    {
        Toast.makeText(PesquisarActivity.this, "Dia inicial: " + diaInicial + " Dia final: " + diaFinal + " Mes Inicial: " + mesInicial + " Ano Inicial" + anoInicial, Toast.LENGTH_LONG).show();
    }
}
