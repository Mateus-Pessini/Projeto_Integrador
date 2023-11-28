package com.example.denticare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.denticare.Adapter.CidadeAdapter;
import com.example.denticare.Adapter.EstadoAdapter;
import com.example.denticare.R;
import com.example.denticare.api.Api.ApiCidade;
import com.example.denticare.api.Api.ApiCliente;
import com.example.denticare.api.Api.ApiEndereco;
import com.example.denticare.api.Api.ApiEstado;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.Cidade;
import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Endereco;
import com.example.denticare.api.models.pessoa.Estado;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadUsuario extends AppCompatActivity {
    private Button btCancel, btSalvar;

    private EditText edNomeCompleto, edTelefone, edCPF, edRG, edRua, edComplemento, edCEP,
            edNumero, edEmail, edBairro, edCro, edEspecialidade, edSenhaUser;

    private Spinner spEstado, spCidade;

    private RadioButton rbDentista, rbRecepcionista;

    private RadioGroup btRadioGroup;

    private TextView tvCro, tvEspecialidade, tvSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadcliente_2);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btCancel = findViewById(R.id.btCancel);
        btSalvar = findViewById(R.id.btSalvar);

        edSenhaUser = findViewById(R.id.editSenhaUser);
        edNomeCompleto = findViewById(R.id.editTextNomeCompleto);
        edTelefone = findViewById(R.id.editTextTelefone);
        edCPF = findViewById(R.id.editTextCPF);
        edRG = findViewById(R.id.editTextRG);
        edRua = findViewById(R.id.editTextRua);
        edComplemento = findViewById(R.id.editTextComplemento);
        edEmail = findViewById(R.id.editTextEmail);
        edCEP = findViewById(R.id.editTextCEP);
        edNumero = findViewById(R.id.editTextNumero);
        edBairro = findViewById(R.id.editTextBairro);
        edCro = findViewById(R.id.editCroUser);
        edEspecialidade = findViewById(R.id.editTextEspecialidade);
        btRadioGroup = findViewById(R.id.btRadioGroup);

        tvCro = findViewById(R.id.tvCro);
        tvEspecialidade = findViewById(R.id.tvEspecialidade);
        tvSenha = findViewById(R.id.tvSenha);

        rbRecepcionista = findViewById(R.id.rbRecepcionista);
        rbDentista = findViewById(R.id.rbDentista);

        spEstado = findViewById(R.id.spinnerEstado);
        spCidade = findViewById(R.id.spinnerCidade);

        Intent myIntent = getIntent(); // gets the previously created intent
        String type = myIntent.getStringExtra("type");
        if (type.equals("client")) {
            edSenhaUser.setVisibility(View.GONE);
            tvSenha.setVisibility(View.GONE);
            btRadioGroup.setVisibility(View.GONE);
            //edEspecialidade.setVisibility(View.GONE);

        }

        rbDentista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                edCro.setVisibility(View.VISIBLE);
                edEspecialidade.setVisibility(View.VISIBLE);
                tvCro.setVisibility(View.VISIBLE);
                tvEspecialidade.setVisibility(View.VISIBLE);
            }
        });

        rbRecepcionista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                edCro.setVisibility(View.GONE);
                edEspecialidade.setVisibility(View.GONE);
                tvCro.setVisibility(View.GONE);
                tvEspecialidade.setVisibility(View.GONE);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("", "" + token);

        if (!token.isEmpty() || type.equals("client")) {
            ApiEstado apiEstado = RetroFit.GET_ALL_ESTADO();

            Call<List<Estado>> estadoCall;
            if (type.equals("client")) {
                estadoCall = apiEstado.GET_ALL_ESTADO_WITHOUT_AUTH();
            } else {
                estadoCall = apiEstado.GET_ALL_ESTADO(token);
            }
            estadoCall.enqueue(new Callback<List<Estado>>() {
                @Override
                public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                    if (response.isSuccessful()) {
                        List<Estado> estados = response.body();
                        //estados.push(-1, "Selecione");
                        EstadoAdapter adapter = new EstadoAdapter(CadUsuario.this, estados);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spEstado.setAdapter(adapter);
                    } else {
                        // Trate o erro de resposta da API, se necessári
                        System.out.println(response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<List<Estado>> call, Throwable t) {
                    // Trate falhas na chamada à API, se necessário
                    System.out.println(t.toString());
                }
            });
        }


        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadUsuario.this, NewLogin.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadUsuario.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCliente apiCliente = RetroFit.REGISTER_CLIENTE();
                ApiEndereco apiEndereco = RetroFit.REGISTER_ENDERECO();
                validaCampos();
                Endereco end = new Endereco();
                Cliente cli = new Cliente();
                end.setCep(edCEP.getText().toString());
                end.setCidade((Cidade) spCidade.getSelectedItem());
                end.setComplemento(edComplemento.getText().toString());
                end.setNmRua(edRua.getText().toString());
                end.setNumero(Integer.parseInt(edNumero.getText().toString()));
                end.setBairro(edBairro.getText().toString());

                cli.setCpf(edCPF.getText().toString());
                cli.setRg(edRG.getText().toString());
                cli.setEmail(edEmail.getText().toString());
                cli.setNome(edNomeCompleto.getText().toString());
                cli.setNrtelefone(edTelefone.getText().toString());
                cli.setEndereco(end);
                //criarDentes(cli);

                Call<Endereco> enderecoCall = apiEndereco.REGISTER_ENDERECO("Bearer " + token, end);
                enderecoCall.enqueue(new Callback<Endereco>() {
                    @Override
                    public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                        if (response.isSuccessful()) {
                            Call<Cliente> clienteCall = apiCliente.REGISTER_CLIENTE("Bearer " + token, cli);
                            clienteCall.enqueue(new Callback<Cliente>() {
                                @Override
                                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(CadUsuario.this, "Cliente cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                                        limparCampos();
                                    } else {
                                        Toast.makeText(CadUsuario.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                                        Log.e("", "Message =" + response.code());
                                        Log.e("", "Body =" + response.body());
                                        Log.e("", "ErroBody =" + response.errorBody());
                                        Log.e("", "response =" + response);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Cliente> call, Throwable t) {
                                    Toast.makeText(CadUsuario.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<Endereco> call, Throwable t) {
                        Toast.makeText(CadUsuario.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Long estadoId = ((Estado) spEstado.getSelectedItem()).getId(); // Supondo que seu objeto Estado tenha um método getId() para obter o ID
                SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                if (!token.isEmpty()) {
                    ApiCidade apiCidade = RetroFit.GET_ALL_BY_ESTADO();
                    Call<List<Cidade>> call = apiCidade.GET_ALL_BY_ESTADO(token, estadoId);
                    call.enqueue(new Callback<List<Cidade>>() {
                        @Override
                        public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                            if (response.isSuccessful()) {
                                List<Cidade> cidades = response.body();
                                CidadeAdapter adapter = new CidadeAdapter(CadUsuario.this, cidades);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spCidade.setAdapter(adapter);
                                spCidade.setClickable(true);
                                Drawable ativo = ContextCompat.getDrawable(CadUsuario.this, R.drawable.borda1);
                                spCidade.setBackground(ativo);
                            } else {
                                // Trate o erro de resposta da API, se necessário
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Cidade>> call, Throwable t) {
                            // Trate falhas na chamada à API, se necessário
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Código para lidar com nenhum item selecionado
            }
        });


    }

    public void limparCampos() {
        edNomeCompleto.setText("");
        edTelefone.setText("");
        edCPF.setText("");
        edRG.setText("");
        edRua.setText("");
        edComplemento.setText("");
        edEmail.setText("");
        edCEP.setText("");
        edNumero.setText("");

        // Limpar os erros dos campos
        edNomeCompleto.setError(null);
        edTelefone.setError(null);
        edCPF.setError(null);
        edRG.setError(null);
        edRua.setError(null);
        edEmail.setError(null);
        edCEP.setError(null);
        edNumero.setError(null);

        // Limpar a seleção dos Spinners
        spEstado.setSelection(0);
        spCidade.setSelection(0);
    }

    public void validaCampos() {
        String nomeCompleto = edNomeCompleto.getText().toString().trim();
        if (nomeCompleto.isEmpty()) {
            edNomeCompleto.setError("Campo obrigatório");
        }

        String telefone = edTelefone.getText().toString().trim();
        if (telefone.isEmpty()) {
            edTelefone.setError("Campo obrigatório");
        }

        String cpf = edCPF.getText().toString().trim();
        if (cpf.isEmpty()) {
            edCPF.setError("Campo obrigatório");
        }

        String rg = edRG.getText().toString().trim();
        if (rg.isEmpty()) {
            edRG.setError("Campo obrigatório");
        }

        String rua = edRua.getText().toString().trim();
        if (rua.isEmpty()) {
            edRua.setError("Campo obrigatório");
        }

        String complemento = edComplemento.getText().toString().trim();

        String email = edEmail.getText().toString().trim();
        if (email.isEmpty()) {
            edEmail.setError("Campo obrigatório");
        }

        String cep = edCEP.getText().toString().trim();
        if (cep.isEmpty()) {
            edCEP.setError("Campo obrigatório");
        }

        String numero = edNumero.getText().toString().trim();
        if (numero.isEmpty()) {
            edNumero.setError("Campo obrigatório");
        }

// Validação para o Spinner de Estado
        if (spEstado.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) spEstado.getSelectedView();
            errorText.setError("Selecione um estado válido");
        }

// Validação para o Spinner de Cidade
        if (spCidade.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) spCidade.getSelectedView();
            errorText.setError("Selecione uma cidade válida");
        }
    }

   /* public void criarDentes(Cliente cliente) {
        for (int i = 1; i <= 32; i++) {
            Dentes dente = new Dentes();
            dente.setNrDente(i);
            dente.setDsDente("Dente " + i);
            dente.setCliente(cliente);
        }
    }*/


}