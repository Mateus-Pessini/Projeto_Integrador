package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button btConsultas;
    private Button btAgendamento;
    private Button btGerarPDF;
    private Button btCadastrarCliente;
    private Button btCadastrarFotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConsultas = findViewById(R.id.btConsultas);
        btAgendamento = findViewById(R.id.btAgendamento);
        btGerarPDF = findViewById(R.id.btGerarPDF);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCadastrarFotos = findViewById(R.id.btCadastrarFotos);

        btConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tela_consulta.class);
                startActivity(intent);
            }
        });
        btAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tela_agendamento.class);
                startActivity(intent);
            }
        });
        btGerarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tela_gerar_pdf.class);
                startActivity(intent);
            }
        });
        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tela_cadastro_cliente.class);
                startActivity(intent);
            }
        });
        btCadastrarFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tela_escolha_cadastro.class);
                startActivity(intent);
            }
        });
    }
}