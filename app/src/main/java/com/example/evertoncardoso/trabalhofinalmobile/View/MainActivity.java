package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.evertoncardoso.trabalhofinalmobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Cancela para nao ficar nada pendente na tela
        setResult(RESULT_CANCELED);

        // Fecha a tela
        finish();
    }
}
