package com.example.denticare.cell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.denticare.DadosDentista;
import com.example.denticare.GeraPDF;
import com.example.denticare.R;
import com.example.denticare.cadastro.AtualizaCad;

public class CadCell extends AppCompatActivity {

    private Button btCancel;
    private Button btAgendarCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cell);

        btAgendarCell = findViewById(R.id.btAgendarCell);
        btCancel = findViewById(R.id.btCancel);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCell.this, InicialCell.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadCell.this, "Seu Agendamento foi Cancelado!", Toast.LENGTH_SHORT).show();

            }
        });
        btAgendarCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCell.this, InicialCell.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadCell.this, "Sua Consulta foi Agendada com Sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}