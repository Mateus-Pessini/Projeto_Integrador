package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tela_login extends AppCompatActivity {

    private TextView tvCadastrar;
    private Button btLogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        tvCadastrar = findViewById(R.id.tvCadastrar);
        btLogar =findViewById(R.id.btLogar);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tela_login.this, tela_cadastro_inicial.class);
                startActivity(intent);
            }
        });

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tela_login.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}