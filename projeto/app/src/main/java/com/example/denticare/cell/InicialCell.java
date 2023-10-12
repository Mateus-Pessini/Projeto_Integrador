package com.example.denticare.cell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.denticare.ConfirLogin;
import com.example.denticare.R;
import com.example.denticare.cadastro.CadCliente;
import com.example.denticare.cadastro.CadInicial;
import com.example.denticare.cadastro.Login;
import com.example.denticare.opcoes.OpcaoCadUsuario;

public class InicialCell extends AppCompatActivity {

    private TextView btAgendarCell;
    private Button btAcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial_cell);
        
        btAgendarCell = findViewById(R.id.btAgendarCell);
        btAcesso = findViewById(R.id.btAcesso);

        btAgendarCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialCell.this, AgendCell.class);
                startActivity(intent);
            }
        });
        btAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirLogin.showCustomDialog(InicialCell.this, new ConfirLogin.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });

    }
}