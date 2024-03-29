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
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.Cidade;
import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Endereco;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.Pessoa;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

    private TextView tvCro, tvEspecialidade, tvSenha, tvTitulo, tvRecep, tvDent;

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
        tvTitulo = findViewById(R.id.tvTitulo);
        tvRecep = findViewById(R.id.tvRecep);
        tvDent = findViewById(R.id.tvDent);
        tvEspecialidade = findViewById(R.id.tvEspecialidade);
        tvSenha = findViewById(R.id.tvSenha);

        rbRecepcionista = findViewById(R.id.rbRecepcionista);
        rbDentista = findViewById(R.id.rbDentista);

        spEstado = findViewById(R.id.spinnerEstado);
        spCidade = findViewById(R.id.spinnerCidade);

        // preencher campos teste //EXCLUIR antes de apresentar
        edSenhaUser.setText("123");
        edNomeCompleto.setText("Equipe xxx");
        edTelefone.setText("99999999");
        edCPF.setText("99999999999");
        edRG.setText("999999999");
        edRua.setText("123");
        edComplemento.setText("123");
        edEmail.setText("equipe@xxx");
        edCEP.setText("99999999");
        edNumero.setText("123");
        edBairro.setText("123");
        edCro.setText("123");
        edEspecialidade.setText("123");

        Intent myIntent = getIntent(); // gets the previously created intent
        String type = myIntent.getStringExtra("type");
        if (type.equals("client")) {
            edSenhaUser.setVisibility(View.GONE);
            edSenhaUser.setText("");
            tvSenha.setVisibility(View.GONE);
            btRadioGroup.setVisibility(View.GONE);
            tvRecep.setVisibility(View.GONE);
            tvDent.setVisibility(View.GONE);
            tvTitulo.setText("Cadastro de Cliente");
            //edEspecialidade.setVisibility(View.GONE);

        }

        rbDentista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    edCro.setVisibility(View.VISIBLE);
                    edEspecialidade.setVisibility(View.VISIBLE);
                    tvCro.setVisibility(View.VISIBLE);
                    tvEspecialidade.setVisibility(View.VISIBLE);
                }
            }
        });

        rbRecepcionista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    edCro.setVisibility(View.GONE);
                    edEspecialidade.setVisibility(View.GONE);
                    tvCro.setVisibility(View.GONE);
                    tvEspecialidade.setVisibility(View.GONE);
                    edCro.setText("");
                    edEspecialidade.setText("");
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("", "" + token);

        //if (!token.isEmpty() || type.equals("client")) {
            ApiEstado apiEstado = RetroFit.GET_ALL_ESTADO();

            Call<List<Estado>> estadoCall = apiEstado.GET_ALL_ESTADO_WITHOUT_AUTH();
            //if (type.equals("client")) {
            //    estadoCall =
            //} else {
            //    estadoCall = apiEstado.GET_ALL_ESTADO(token);
            //}
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
        //}


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
                ApiPessoa apiPessoa = RetroFit.REGISTER_PESSOA();

                validaCampos();
                Pessoa pessoa = new Pessoa();
                //this.ftPerfil = ftPerfil;

                pessoa.setNome(edNomeCompleto.getText().toString());
                pessoa.setCpf(edCPF.getText().toString());
                pessoa.setRg(edRG.getText().toString());
                pessoa.setNrtelefone(edTelefone.getText().toString());
                pessoa.setEmail(edEmail.getText().toString());
                pessoa.setSenha(edSenhaUser.getText().toString());
                if (rbDentista.isChecked()) {
                    pessoa.setTpPessoa(TpPessoaEnum.DENTISTA);
                } else if (rbRecepcionista.isChecked()) {
                    pessoa.setTpPessoa(TpPessoaEnum.SECRETARIA);
                } else {
                    pessoa.setTpPessoa(TpPessoaEnum.CLIENTE);
                }

                pessoa.setCro(edCro.getText().toString());
                pessoa.setEspecialidade(edEspecialidade.getText().toString());

                pessoa.setNmRua(edRua.getText().toString());
                pessoa.setBairro(edBairro.getText().toString());
                pessoa.setNumero(Integer.parseInt(edNumero.getText().toString()));
                pessoa.setComplemento(edComplemento.getText().toString());
                pessoa.setCep(edCEP.getText().toString());

                pessoa.setCidade((Cidade) spCidade.getSelectedItem());

                //criarDentes(cli);
                SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                Log.e("", "" + token);
                Call<Pessoa> pessoaCall = apiPessoa.REGISTER_PESSOA_WITHOUT_AUTH(pessoa);
                //Call<Pessoa> pessoaCall = apiPessoa.REGISTER_PESSOA(token, pessoa);
                pessoaCall.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CadUsuario.this, "Usuário/Cliente cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                            limparCampos();
                            Intent intent = new Intent(CadUsuario.this, NewLogin.class);
                            startActivity(intent);
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
                            if (jObjError.toString().contains("CPF") && jObjError.toString().contains("E-mail")) {
                                Toast.makeText(CadUsuario.this, "Cliente/Usuário com este CPF e com este E-mail já está registrado. Verifique com o consultório!", Toast.LENGTH_LONG).show();
                            } else if (jObjError.toString().contains("CPF")) {
                                Toast.makeText(CadUsuario.this, "Cliente/Usuário com este CPF já está registrado. Verifique com o consultório!", Toast.LENGTH_LONG).show();
                            } else if (jObjError.toString().contains("E-mail")) {
                                Toast.makeText(CadUsuario.this, "Cliente/Usuário com este E-mail já está registrado. Verifique com o consultório!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(CadUsuario.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("", "Message =" + response.code());
                            Log.e("", "Body =" + response.body());
                            Log.e("", "ErroBody =" + response.errorBody());
                            Log.e("", "response =" + response);
                        }

                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
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
                //String token = sharedPreferences.getString("token", "");
                //if (!token.isEmpty()) {
                    ApiCidade apiCidade = RetroFit.GET_ALL_BY_ESTADO();
                    Call<List<Cidade>> call = apiCidade.GET_ALL_BY_ESTADO_WITHOUT_AUTH(estadoId);
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
                //}
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
        edBairro.setText("");
        edEspecialidade.setText("");
        edCro.setText("");
        edSenhaUser.setText("");
        rbDentista.setChecked(false);
        rbRecepcionista.setChecked(false);

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