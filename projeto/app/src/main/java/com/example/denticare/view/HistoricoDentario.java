package com.example.denticare.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;

public class HistoricoDentario extends AppCompatActivity {

    private Button btVerFotos;
    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_dentario);

        NavigationUtil.hideNavigation(this);

        btVerFotos = findViewById(R.id.btVerFotos);
        btVoltar = findViewById(R.id.btVoltar);

        btVerFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, AddFoto.class);
                startActivity(intent);
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, InicialConsulta.class);
                startActivity(intent);
            }
        });
    }
}