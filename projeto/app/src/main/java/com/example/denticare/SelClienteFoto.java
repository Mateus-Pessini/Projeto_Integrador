package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.denticare.agendamento.Agenda;
import com.example.denticare.agendamento.Consulta;
import com.example.denticare.cadastro.AtualizaCad;
import com.example.denticare.cadastro.Login;
import com.example.denticare.opcoes.OpcaoCadUsuario;

public class SelClienteFoto extends AppCompatActivity {

    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private Button btAddFotoContinuar;
    private LinearLayout btCadClienteRecep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selclientefoto);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btAddFotoContinuar = findViewById(R.id.btAddFotoContinuar);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelClienteFoto.this, Consulta.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelClienteFoto.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelClienteFoto.this, GeraPDF.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcaoCadUsuario.showCustomDialog(SelClienteFoto.this, new OpcaoCadUsuario.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelClienteFoto.this, Login.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelClienteFoto.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btAddFotoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelClienteFoto.this, AddFoto.class);
                startActivity(intent);
            }
        });


    }
}