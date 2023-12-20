package com.example.denticare.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.api.models.pessoa.PreAgendamento;
import com.example.denticare.util.DataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Consulta2 extends AppCompatActivity {

    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private LinearLayout btCadClienteRecep;
    private Button btDelete;
    private TextView tvDentes;
    private ImageView ivImgDentista;

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
        btDelete = findViewById(R.id.buttonDelete);
        tvDentes = findViewById(R.id.TvConsultaDentes);
        ivImgDentista = findViewById(R.id.ivImgDentista);

      /*  buscaTipoUsuario();

        String token = DataUtils.getToken(Consulta2.this);
        ApiPreAgendamento apiPreAgendamento = RetroFit.GET_ALL_PRE_AGENDAMENTO();

        Call<List<ConsultaList>> preAgendamentoCall = apiPreAgendamento.GET_ALL_PRE_AGENDAMENTO(token);
        preAgendamentoCall.enqueue(new Callback<List<ConsultaList>>() {
            @Override
            public void onResponse(Call<List<ConsultaList>> call, Response<List<ConsultaList>> response) {
                if (response.isSuccessful()) {
                    Log.e("", "Message =" + response.code());
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
/*
                } else {
                    Toast.makeText(Consulta2.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                    Log.e("", "Message =" + response.code());
                    Log.e("", "Body =" + response.body());
                    Log.e("", "ErroBody =" + response.errorBody());
                    Log.e("", "response =" + response);
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaList>> call, Throwable t) {
                Toast.makeText(Consulta2.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
            }
        });

 */

        tvDentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta2.this, InicialConsulta.class);
                startActivity(intent);
            }
        });

        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta2.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta2.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta2.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta2.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta2.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcaoCadUsuario.showCustomDialog(Consulta2.this, new OpcaoCadUsuario.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });

    }
    private void buscaTipoUsuario(){
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