package com.example.denticare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

public class HistoricoDentario extends AppCompatActivity {

    private Button btVoltar,btEdit;
    private TextView tvNome, tvConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_dentario);

        NavigationUtil.hideNavigation(this);

        btVoltar = findViewById(R.id.btVoltar);
        btEdit = findViewById(R.id.buttonEdit);
        tvNome = findViewById(R.id.tvNome);
        tvConsulta = findViewById(R.id.TvConsultaDentes);

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if (!token.isEmpty()) {
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] tokenSplited = token.split("\\.");
            String header = new String(decoder.decode(tokenSplited[0]));
            String payload = new String(decoder.decode(tokenSplited[1]));
            String name;
            try {
                name = new JSONObject(payload).getString("Name");
            } catch (JSONException e) {
                name = "";
            }
            tvNome.setText(name);
        }

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, InicialConsulta.class);
                startActivity(intent);
            }
        });

        tvConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, DentesInfo.class);
                intent.putExtra("edit", "VISUALIZAR");
                startActivity(intent);
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, DentesInfo.class);
                intent.putExtra("edit", "EDIT");
                startActivity(intent);
            }
        });
    }
}