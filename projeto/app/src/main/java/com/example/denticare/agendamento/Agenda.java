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

import com.example.denticare.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class Agenda extends AppCompatActivity {

    TextView tvMes;
    TextView tvAno;
    private TextView[] dayTextViews = new TextView[42]; // 42 (6x7) para acomodar todos os dias possíveis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        tvMes = findViewById(R.id.tvMes);
        tvAno = findViewById(R.id.tvAno);

        // Inicialize as referências para as TextViews de dia da semana
        for (int i = 0; i < 42; i++) {
            String dayTextViewId = "tvDomingo" + (i + 1);
            int resId = getResources().getIdentifier(dayTextViewId, "id", getPackageName());
            dayTextViews[i] = findViewById(resId);
        }

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        updateCalendar(currentMonth, currentYear);









}

    private void updateCalendar(int month, int year) {
        // Atualize o mês e ano nas TextViews correspondentes
        tvMes.setText(getMonthName(month));
        tvAno.setText(String.valueOf(year));

        // Lógica para preencher os dias do mês nas TextViews
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Configura o calendário para o primeiro dia do mês

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Número de dias no mês
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Dia da semana do primeiro dia do mês

        // Preencha os dias do mês nas TextViews
        int dayCounter = 1; // Contador para os dias do mês
        for (int i = 0; i < 42; i++) {
            if (i < firstDayOfWeek - 1 || dayCounter > daysInMonth) {
                dayTextViews[i].setText(""); // Deixe a TextView vazia para dias anteriores ou posteriores ao mês
            } else {
                dayTextViews[i].setText(String.valueOf(dayCounter)); // Preencha a TextView com o dia atual do mês
                dayCounter++;
            }
        }
    }
    private String getMonthName(int month) {
        String[] monthNames = {
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        if (month >= 1 && month <= 12) {
            return monthNames[month - 1];
        } else {
            return "";
        }
    }



}