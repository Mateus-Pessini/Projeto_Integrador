package com.example.denticare.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiPreAgendamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.api.models.pessoa.PreAgendamento;
import com.example.denticare.util.DataUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Consulta extends AppCompatActivity {

    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private LinearLayout btCadClienteRecep;
    private TableLayout tableLayout;
    private Button btProximo;
    private TextView tvNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        btProximo = findViewById(R.id.btProximo);
        tvNome = findViewById(R.id.tvNomeEmConsulta);

        tvNome.setText(DataUtils.getDataFromTokenToShow(Consulta.this, "name"));

        tableLayout = findViewById(R.id.tableLayout);

        String token = DataUtils.getToken(Consulta.this);
        ApiPreAgendamento apiPreAgendamento = RetroFit.GET_ALL_PRE_AGENDAMENTO();

        Call<List<ConsultaList>> preAgendamentoCall = apiPreAgendamento.GET_ALL_PRE_AGENDAMENTO(token);
        preAgendamentoCall.enqueue(new Callback<List<ConsultaList>>() {
            @Override
            public void onResponse(Call<List<ConsultaList>> call, Response<List<ConsultaList>> response) {
                if (response.isSuccessful()) {
                    /*Log.e("", "Message =" + response.code());
                    ArrayList<PreAgendamento> dados = new ArrayList<>();
                    for (int i = 0; i < dados.size(); i++) {
                        TableRow row = new TableRow(Consulta.this);
                        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT
                        );
                        row.setLayoutParams(layoutParams);

                        for (int j = 0; j < dados[i].length; j++) {
                            TextView cell = new TextView(Consulta.this);
                            cell.setText(dados[i][j]);
                            cell.setPadding(10, 10, 10, 10);
                            row.addView(cell);
                        }

                        tableLayout.addView(row);
                    }

                    Toast.makeText(Consulta.this, "Dados Obtidos com Sucesso!", Toast.LENGTH_SHORT).show();*/

                } else {
                    Toast.makeText(Consulta.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                    Log.e("", "Message =" + response.code());
                    Log.e("", "Body =" + response.body());
                    Log.e("", "ErroBody =" + response.errorBody());
                    Log.e("", "response =" + response);
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaList>> call, Throwable t) {
                Toast.makeText(Consulta.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
            }
        });


        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, InicialConsulta.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, Login.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcaoCadUsuario.showCustomDialog(Consulta.this, new OpcaoCadUsuario.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });

    }
}