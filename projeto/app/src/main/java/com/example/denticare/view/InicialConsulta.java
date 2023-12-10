package com.example.denticare.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;

public class InicialConsulta extends AppCompatActivity {

    private Button btHisDentario;
    private Button btFimConsulta;
    private Button btIniConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial_consulta);

        NavigationUtil.hideNavigation(this);

        btFimConsulta = findViewById(R.id.btFimConsulta);
        btIniConsulta = findViewById(R.id.btIniConsulta);
        btHisDentario = findViewById(R.id.btHisDentario);

        btFimConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialConsulta.this, Consulta.class);
                startActivity(intent);
            }
        });
        btIniConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialConsulta.this, EscolhaDente.class);
                intent.putExtra("pessoa","3");
                startActivity(intent);
            }
        });
        btHisDentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialConsulta.this, HistoricoDentario.class);
                startActivity(intent);
            }
        });
    }
}