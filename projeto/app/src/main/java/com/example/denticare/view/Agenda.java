package com.example.denticare.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.Adapter.PreAgendamentoAdapter;
import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPreAgendamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.PreAgendamento;
import com.example.denticare.util.DataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agenda extends AppCompatActivity {

    private LinearLayout btConsultaRecep;
    private LinearLayout btCadClienteRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private LinearLayout btAgendarRecep;
    private Button btnData;
    private EditText edtData;
    private GridView gvAgenda;
    private SimpleDateFormat dateFormatter;
    private TextView[] dayTextViews = new TextView[42];
    private TextView tvNome;
    private ImageView ivImgDentista;
    private PreAgendamentoAdapter preAgendamentoAdapter;
    private List<ConsultaList> listaPreAgendamento;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btMeusDados = findViewById(R.id.btMeusDados);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btnData = findViewById(R.id.btnData);
        edtData = findViewById(R.id.edtData);
        gvAgenda = findViewById(R.id.gvAgenda);
        tvNome = findViewById(R.id.tvNome);
        ivImgDentista = findViewById(R.id.ivImgDentista);

        buscaTipoUsuario();

        listaPreAgendamento = new ArrayList<>();
        preAgendamentoAdapter = new PreAgendamentoAdapter(this, listaPreAgendamento);
        gvAgenda.setAdapter(preAgendamentoAdapter);


        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPreAgendamento apiPreAgendamento = RetroFit.GET_ALL_PRE_AGENDAMENTO();

        Call<List<ConsultaList>> call = apiPreAgendamento.GET_ALL_PRE_AGENDAMENTO("Bearer " + token);
        call.enqueue(new Callback<List<ConsultaList>>() {
            @Override
            public void onResponse(Call<List<ConsultaList>> call, Response<List<ConsultaList>> response) {
                if(response.isSuccessful()){
                    List<ConsultaList> listaConsulta = response.body();
                    if(listaConsulta != null){
                        // Limpar a lista antes de adicionar novos itens
                        listaPreAgendamento.clear();

                        // Adicionar cada item individualmente à lista
                        for (ConsultaList consulta : listaConsulta) {
                            listaPreAgendamento.add(consulta);
                        }

                        preAgendamentoAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("nao deu", "Erro na resposta: " + response.message());
                }
            }


            @Override
            public void onFailure(Call<List<ConsultaList>> call, Throwable t) {
                Log.e("nao deu", "Erro na requisição: " + t.getMessage());
            }
        });




        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agenda.this, Consulta2.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agenda.this, Clientes.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agenda.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agenda.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agenda.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agenda.this, DadosDentista.class);
                startActivity(intent);
            }
        });

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        edtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }
    private void showDatePickerDialog() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Agenda.this, // Use "Agenda.this" para obter o contexto da atividade
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        String selectedDate = dateFormatter.format(selectedCalendar.getTime());
                        edtData.setText(selectedDate);
                    }
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
        );


        // Mostrar o diálogo de seleção de data
        datePickerDialog.show();
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

    public void retornapreAgendamento(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPreAgendamento apiPreAgendamento = RetroFit.GET_ALL_PRE_AGENDAMENTO();

        Call<List<ConsultaList>> call = apiPreAgendamento.GET_ALL_PRE_AGENDAMENTO("Bearer " + token);
        call.enqueue(new Callback<List<ConsultaList>>() {
            @Override
            public void onResponse(Call<List<ConsultaList>> call, Response<List<ConsultaList>> response) {
                if(response.isSuccessful()){
                    List<ConsultaList> listaConsulta = response.body();
                    for (ConsultaList consulta : listaConsulta) {
                        // Faça algo com cada objeto ConsultaList aqui
                        Log.e("", "" + consulta.getId()+ consulta.getData());
                    }
                } else {
                    Log.e("nao deu", "Erro na resposta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaList>> call, Throwable t) {
                Log.e("nao deu", "Erro na requisição: " + t.getMessage());
            }
        });
    }


}


