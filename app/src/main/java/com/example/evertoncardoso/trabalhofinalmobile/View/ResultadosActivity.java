package com.example.evertoncardoso.trabalhofinalmobile.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evertoncardoso.trabalhofinalmobile.Controller.ItemsController;
import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;
import com.example.evertoncardoso.trabalhofinalmobile.R;

import java.util.ArrayList;
import java.util.List;

public class ResultadosActivity extends AppCompatActivity {

    ListView listViewItens;
    TextView txtIntervalo;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_resultados);

        listViewItens = findViewById(R.id.listViewItems);

        txtIntervalo = findViewById(R.id.txtIntervalo);

        listarItens(PesquisarActivity.listaPeneirada);

        listViewItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) listViewItens.getItemAtPosition(position);

                //Toast.makeText(MainActivity.this, conteudo, Toast.LENGTH_LONG).show();
                String index = conteudo.substring(0, conteudo.indexOf("."));

                Item item = ItemsController.retornaItem(Integer.parseInt(index));
            }
        });

        txtIntervalo.setText(PesquisarActivity.data);
    }

    public void listarItens(List<Item> lista)
    {
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(ResultadosActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewItens.setAdapter(adapter);

        for(Item i: lista)
        {
            arrayList.add(i.getId() +  ". " + i.getDescricao() + " - " + i.getTipo() + " - " + i.getValor());
            adapter.notifyDataSetChanged();
        }
    }
}
