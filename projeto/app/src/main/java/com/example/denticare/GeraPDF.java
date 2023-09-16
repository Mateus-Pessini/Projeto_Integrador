package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GeraPDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerapdf);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

    }
}