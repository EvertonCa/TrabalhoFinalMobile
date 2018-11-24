package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.ItemsController;
import com.example.evertoncardoso.trabalhofinalmobile.R;

public class EditaItemActivity extends AppCompatActivity {

    TextView labelDescricao, labelTipo, labelCategoria, labelData;
    EditText campoValor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_item);

        labelDescricao = findViewById(R.id.labelDescricao);
        labelTipo = findViewById(R.id.labelCategoria);
        labelCategoria = findViewById(R.id.labelCategoria);
        labelData = findViewById(R.id.labelData);
        campoValor = findViewById(R.id.campoValor);

        labelDescricao.setText(ResultadosActivity.item.getDescricao());
        labelTipo.setText(ResultadosActivity.item.getTipo());
        labelCategoria.setText(ResultadosActivity.item.getCategoria());
        labelData.setText(ResultadosActivity.item.getDescricao());
        campoValor.setText(String.valueOf(ResultadosActivity.item.getValor()));
    }

    public void editarItem(View view)
    {
        ResultadosActivity.item.setValor(Double.parseDouble(campoValor.getText().toString()));
        ItemsController.editaItem(ResultadosActivity.item);
        Toast.makeText(EditaItemActivity.this, "Editado com Sucesso!", Toast.LENGTH_LONG).show();
        chamaPesquisa();
    }

    public void apagarItem(View view)
    {
        ItemsController.apagaItem(ResultadosActivity.item);
        Toast.makeText(EditaItemActivity.this, "Apagado com Sucesso!", Toast.LENGTH_LONG).show();
        chamaPesquisa();
    }

    public void chamaPesquisa()
    {
        startActivity(new Intent(this, PesquisarActivity.class));
    }
}
