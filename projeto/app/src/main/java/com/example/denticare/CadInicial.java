package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CadInicial extends AppCompatActivity {

    private TextView tvLogin;
    private Button btLogar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadinicial);

        tvLogin = findViewById(R.id.tvLogin);
        btLogar1 = findViewById(R.id.btLogar1);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadInicial.this, Login.class);
                startActivity(intent);
            }
        });

        btLogar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadInicial.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}