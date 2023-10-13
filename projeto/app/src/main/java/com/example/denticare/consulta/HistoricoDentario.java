package com.example.denticare.consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.denticare.AddFoto;
import com.example.denticare.MainActivity;
import com.example.denticare.NavigationUtil;
import com.example.denticare.R;
import com.example.denticare.agendamento.Consulta;

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