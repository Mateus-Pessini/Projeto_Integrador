package com.example.denticare.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;

public class AgendaCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendacliente);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

    }
}