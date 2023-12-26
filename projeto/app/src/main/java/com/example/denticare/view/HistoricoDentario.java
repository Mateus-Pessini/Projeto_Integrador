package com.example.denticare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;
import com.example.denticare.api.models.enums.TpPessoaEnum;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

public class HistoricoDentario extends AppCompatActivity {

    private Button btVoltar,btEdit;
    private TextView tvNome, tvConsulta;
    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private LinearLayout btCadClienteRecep;
    private ImageView ivImgDentista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_dentario);

        NavigationUtil.hideNavigation(this);

        btVoltar = findViewById(R.id.btVoltar);
        btEdit = findViewById(R.id.buttonEdit);
        tvNome = findViewById(R.id.tvNome);
        tvConsulta = findViewById(R.id.TvConsultaDentes);
        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        ivImgDentista = findViewById(R.id.ivImgDentista);

        buscaTipoUsuario();

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
        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, Consulta2.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDentario.this, Clientes.class);
                startActivity(intent);
            }
        });
    }

    private void buscaTipoUsuario() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String role = "";
        if (!token.isEmpty()) {
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] tokenSplited = token.split("\\.");
            String header = new String(decoder.decode(tokenSplited[0]));
            String payload = new String(decoder.decode(tokenSplited[1]));
            String name;
            try {
                name = new JSONObject(payload).getString("Name");
                role = new JSONObject(payload).getString("Role");
            } catch (JSONException e) {
                name = "";
            }
            tvNome.setText(name);
            if (role.equals(TpPessoaEnum.DENTISTA.toString())) {
                btCadClienteRecep.setVisibility(View.GONE);
                btAgendarRecep.setVisibility(View.GONE);
                Log.d("TipoUsuario", "Usuário é um Dentista");
            } else if (role.equals(TpPessoaEnum.SECRETARIA.toString())) {
                btMeusDados.setVisibility(View.GONE);
                ivImgDentista.setVisibility(View.INVISIBLE);
                //tvNomeDentista.setVisibility(View.GONE);
                Log.d("TipoUsuario", "Usuário é uma Secretária");
            } else {

            }
        }
    }
}