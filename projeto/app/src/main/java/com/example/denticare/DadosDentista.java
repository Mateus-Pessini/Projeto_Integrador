package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.denticare.agendamento.Agenda;
import com.example.denticare.agendamento.Consulta;
import com.example.denticare.cadastro.Login;

public class DadosDentista extends AppCompatActivity {

    private Button btDdCancel;
    private Button btDdSalvar;
    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_dentista);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btDdCancel = findViewById(R.id.btDdCancel);
        btDdSalvar = findViewById(R.id.btDdSalvar);
        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);

        btDdCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, MainActivity.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(DadosDentista.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });

        btDdSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, MainActivity.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(DadosDentista.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Consulta.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Login.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, DadosDentista.class);
                startActivity(intent);
            }
        });

    }
}