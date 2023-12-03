package com.example.denticare.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiCliente;
import com.example.denticare.api.Api.ApiEndereco;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiPreAgendamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.Cidade;
import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Endereco;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroPreAgendamento extends AppCompatActivity {

    private TextView tvCadastrar;
    private Button btCancel, btSalvar;
    private EditText edCPF, edDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendapreatendimento);

        tvCadastrar = findViewById(R.id.tvCadastrarCliente);
        edCPF = findViewById(R.id.editTextCPF);
        edDateTime = findViewById(R.id.edDateTime);
        btCancel = findViewById(R.id.btCancel);
        btSalvar = findViewById(R.id.btSalvar);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        edDateTime.setInputType(InputType.TYPE_NULL);

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("", "" + token);

        edDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(edDateTime);
            }
        });

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroPreAgendamento.this, CadUsuario.class);
                intent.putExtra("type","client");
                startActivity(intent);
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroPreAgendamento.this, NewLogin.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadastroPreAgendamento.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroPreAgendamento.this, NewLogin.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadastroPreAgendamento.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPreAgendamento apiPreAgendamento = RetroFit.REGISTER_PRE_AGENDAMENTO();
                ApiPessoa apiPessoa = RetroFit.REGISTER_PESSOA();

                validaCamposPreAgendamento();
                PreAgendamento pre = new PreAgendamento();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                pre.setData(edDateTime.getText().toString());

                Call<Pessoa> pessoaCall = apiPessoa.GET_PESSOA_BY_CPF(edCPF.getText().toString());
                pessoaCall.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if (response.isSuccessful()) {
                            Pessoa pessoa = new Pessoa();
                            //pessoa = response.body();
                            pessoa.setId(response.body().getId());
                            pre.setPessoa(pessoa);
                            Call<PreAgendamento> preAgendamentoCall = apiPreAgendamento.REGISTER_PRE_AGENDAMENTO(pre);
                            preAgendamentoCall.enqueue(new Callback<PreAgendamento>() {
                                @Override
                                public void onResponse(Call<PreAgendamento> call, Response<PreAgendamento> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(CadastroPreAgendamento.this, "Pré-Agendamento cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CadastroPreAgendamento.this, NewLogin.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(CadastroPreAgendamento.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                                        Log.e("", "Message =" + response.code());
                                        Log.e("", "Body =" + response.body());
                                        Log.e("", "ErroBody =" + response.errorBody());
                                        Log.e("", "response =" + response);
                                    }
                                }

                                @Override
                                public void onFailure(Call<PreAgendamento> call, Throwable t) {
                                    Toast.makeText(CadastroPreAgendamento.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            JSONObject jObjError;
                            try {
                                jObjError = new JSONObject(response.errorBody().string());
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Log.e("",jObjError.toString());
                            if (jObjError.toString().contains("Cpf")) {
                                Toast.makeText(CadastroPreAgendamento.this, "Não foi encontrado Cliente com o CPF informado. Verifique ou Se cadastre!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(CadastroPreAgendamento.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("", "Message =" + response.code());
                            Log.e("", "Body =" + response.body());
                            Log.e("", "ErroBody =" + response.errorBody());
                            Log.e("", "response =" + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Toast.makeText(CadastroPreAgendamento.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar= Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));//Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        sdf.setLenient(false);
                        sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

                        date_time_in.setText(sdf.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(CadastroPreAgendamento.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(CadastroPreAgendamento.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void validaCamposPreAgendamento() {
        String cpf = edCPF.getText().toString().trim();
        if (cpf.isEmpty()) {
            edCPF.setError("Campo obrigatório");
        }

        String edDate = edDateTime.getText().toString().trim();
        if (edDate.isEmpty()) {
            edDateTime.setError("Campo obrigatório");
        }
    }
}