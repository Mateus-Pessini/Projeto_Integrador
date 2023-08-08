package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView btConsultas;
    private TextView btAgendamento;
    private TextView btGerarPDF;
    private TextView btCadastrarCliente;
    private TextView btCadastrarFotos;
    private TextView btSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConsultas = findViewById(R.id.btConsultas);
        btAgendamento = findViewById(R.id.btAgendamento);
        btGerarPDF = findViewById(R.id.btGerarPDF);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCadastrarFotos = findViewById(R.id.btCadastrarFotos);
        btSair = findViewById(R.id.btSair);

        btConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consulta.class);
                startActivity(intent);
            }
        });
        btAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agenda.class);
                startActivity(intent);
            }
        });
        btGerarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GeraPDF.class);
                startActivity(intent);
            }
        });
        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpcoesCad.class);
                startActivity(intent);
            }
        });
        btCadastrarFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}