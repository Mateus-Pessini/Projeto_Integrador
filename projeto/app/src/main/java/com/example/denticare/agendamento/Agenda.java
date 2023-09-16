package com.example.denticare.agendamento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denticare.NavigationUtil;
import com.example.denticare.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class Agenda extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

    }

}