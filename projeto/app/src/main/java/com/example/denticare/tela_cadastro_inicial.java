package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tela_cadastro_inicial extends AppCompatActivity {

    private TextView tvLogin;
    private Button btLogar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_inicial);

        tvLogin = findViewById(R.id.tvLogin);
        btLogar1 = findViewById(R.id.btLogar1);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tela_cadastro_inicial.this, tela_login.class);
                startActivity(intent);
            }
        });

        btLogar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tela_cadastro_inicial.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}