package com.example.denticare.cell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.denticare.R;

public class InicialCell extends AppCompatActivity {

    private TextView btAgendarCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial_cell);
        
        btAgendarCell = findViewById(R.id.btAgendarCell);

        btAgendarCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialCell.this, AgendCell.class);
                startActivity(intent);
            }
        });

    }
}