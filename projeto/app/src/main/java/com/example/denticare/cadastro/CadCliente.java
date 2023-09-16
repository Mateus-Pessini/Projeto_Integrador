package com.example.denticare.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.denticare.NavigationUtil;
import com.example.denticare.R;

public class CadCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadcliente);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

    }
}