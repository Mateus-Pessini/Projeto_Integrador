package com.example.denticare.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.denticare.view.NavigationUtil;
import com.example.denticare.R;

public class AgendaHorario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendahorario);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

    }
}