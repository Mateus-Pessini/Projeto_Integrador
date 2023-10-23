package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.denticare.agendamento.Agenda;
import com.example.denticare.agendamento.Consulta;
import com.example.denticare.api.models.user.UsuarioDTO;
import com.example.denticare.api.models.user.UsuarioRole;
import com.example.denticare.cadastro.Login;
import com.example.denticare.opcoes.OpcaoCadUsuario;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private LinearLayout btCadClienteRecep;
    private ImageView ivImgDentista;
    private TextView tvNomeDentista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationUtil.hideNavigation(this);

        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        ivImgDentista = findViewById(R.id.ivImgDentista);
        tvNomeDentista = findViewById(R.id.tvNomeDentista);

        UsuarioRole role = UsuarioRole.DENTISTA;
        UsuarioRole usuario = UsuarioRole.SECRETARIA;

        if (role.equals(UsuarioRole.DENTISTA.getRole())) {
            btCadClienteRecep.setVisibility(View.GONE);
            btAgendarRecep.setVisibility(View.GONE);
            Log.d("TipoUsuario", "Usuário é um Dentista");
        } else if (usuario.equals(UsuarioRole.SECRETARIA)) {
            btMeusDados.setVisibility(View.GONE);
            ivImgDentista.setVisibility(View.INVISIBLE);
            tvNomeDentista.setVisibility(View.GONE);
            Log.d("TipoUsuario", "Usuário é uma Secretária");
        } else {

        }


        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consulta.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcaoCadUsuario.showCustomDialog(MainActivity.this, new OpcaoCadUsuario.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });
    }
}