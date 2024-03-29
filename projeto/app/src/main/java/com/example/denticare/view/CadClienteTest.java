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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.denticare.api.models.pessoa.Dentes;
import com.example.denticare.api.models.pessoa.Endereco;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.Pessoa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadClienteTest extends AppCompatActivity {

    private LinearLayout btAgendarRecep, btSair, btMeusDados, btPdfRecep, btCadFotoRecep, btConsultaRecep, btCadClienteRecep;
    private Button btCancel, btSalvar;

    private EditText edNomeCompleto, edTelefone, edCPF, edRG, edRua, edComplemento, edCEP, edNumero, edEmail, edBairro;

    private Spinner spEstado, spCidade;
    private TextView tvNome;
    private ImageView ivImgDentista;

    private long idPessoa;
    private boolean isEditando = false;

    private List<Estado> estados;

    private Pessoa pessoaCarregada;

    @Override
    public void onResume() {
        super.onResume();
        carregaDados(idPessoa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadcliente);

        //pega o id do cliente clicado
        idPessoa = getIntent().getLongExtra("id_cliente", 0);

        if (idPessoa == 0) {
            isEditando = false;
            Log.d("TST", "Caiu no novo");

        } else {
            isEditando = true;
            Log.d("TST", "Caiu no editando");

            carregaDados(idPessoa);


        }


        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        btCancel = findViewById(R.id.btCancel);
        btSalvar = findViewById(R.id.btSalvar);
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
        spEstado = findViewById(R.id.spinnerEstado);
        spCidade = findViewById(R.id.spinnerCidade);
        tvNome = findViewById(R.id.tvNome);
        ivImgDentista = findViewById(R.id.ivImgDentista);

        buscaTipoUsuario();

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("", "" + token);

        if (!token.isEmpty()) {
            ApiEstado apiEstado = RetroFit.GET_ALL_ESTADO();

            Call<List<Estado>> estadoCall = apiEstado.GET_ALL_ESTADO(token);
            estadoCall.enqueue(new Callback<List<Estado>>() {
                @Override
                public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                    if (response.isSuccessful()) {
                        estados = response.body();
                        EstadoAdapter adapter = new EstadoAdapter(CadClienteTest.this, estados);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spEstado.setAdapter(adapter);
                    } else {
                        // Trate o erro de resposta da API, se necessário
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


        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, Consulta2.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, Clientes.class);
                startActivity(intent);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteTest.this, Clientes.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadClienteTest.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCliente apiCliente = RetroFit.REGISTER_CLIENTE();
                ApiEndereco apiEndereco = RetroFit.REGISTER_ENDERECO();
                boolean isErros = validaCampos();
                Endereco end = new Endereco();
                Cliente cli = new Cliente();
                end.setCep(edCEP.getText().toString());
                end.setCidade((Cidade) spCidade.getSelectedItem());
                end.setComplemento(edComplemento.getText().toString());
                end.setNmRua(edRua.getText().toString());
                if (edNumero.getText().toString().equals("")) {
                    end.setNumero(0);
                } else {
                    end.setNumero(Integer.parseInt(edNumero.getText().toString()));
                }
                end.setBairro(edBairro.getText().toString());

                cli.setCpf(edCPF.getText().toString());
                cli.setRg(edRG.getText().toString());
                /*cli.setEmail(edEmail.getText().toString());
                cli.setNome(edNomeCompleto.getText().toString());
                cli.setNrtelefone(edTelefone.getText().toString());*/
                cli.setEndereco(end);
                //criarDentes(cli);


                if (!isErros) {
                    if (!isEditando) {

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
                                                Toast.makeText(CadClienteTest.this, "Cliente cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                                                limparCampos();
                                                Log.e("nao deu", "deu certo mas nao aparece: " + response.message());
                                            } else {
                                                Toast.makeText(CadClienteTest.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                                                Log.e("", "Message =" + response.code());
                                                Log.e("", "Body =" + response.body());
                                                Log.e("", "ErroBody =" + response.errorBody());
                                                Log.e("", "response =" + response);
                                                Log.e("nao deu", "Erro na resposta: " + response.message());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cliente> call, Throwable t) {
                                            Toast.makeText(CadClienteTest.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {

                                }
                            }

                            @Override
                            public void onFailure(Call<Endereco> call, Throwable t) {
                                Toast.makeText(CadClienteTest.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                                Log.e("nao deu", "Erro na requisição: " + t.getMessage());
                            }
                        });

                    } else {

                        Pessoa pessoaEditada = new Pessoa();
                        pessoaEditada.setId(idPessoa);
                        pessoaEditada.setNome(edNomeCompleto.getText().toString());
                        pessoaEditada.setComplemento(edComplemento.getText().toString());
                        pessoaEditada.setEmail(edEmail.getText().toString());
                        pessoaEditada.setCpf(edCPF.getText().toString());
                        pessoaEditada.setRg(edRG.getText().toString());
                        pessoaEditada.setBairro(edBairro.getText().toString());
                        pessoaEditada.setNmRua(edRua.getText().toString());
                        pessoaEditada.setCep(edCEP.getText().toString());
                        pessoaEditada.setComplemento(edNomeCompleto.getText().toString());
                        pessoaEditada.setNrtelefone(edTelefone.getText().toString());

                        if (edNumero.getText().toString().equals("")) {
                            pessoaEditada.setNumero(0);
                        } else {
                            pessoaEditada.setNumero(Integer.parseInt(edNumero.getText().toString()));
                        }

                        Log.d("TST", "Editando a pessoa " + pessoaEditada.toString());

                        ApiPessoa apiPessoa = RetroFit.REGISTER_PESSOA();
                        Call<Pessoa> clienteCall = apiPessoa.PUT_PESSOA_CLIENTE("Bearer " + token, pessoaEditada);
                        clienteCall.enqueue(new Callback<Pessoa>() {
                            @Override
                            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                                Toast.makeText(CadClienteTest.this, "Editado com sucesso.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CadClienteTest.this, Clientes.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Pessoa> call, Throwable t) {
                                Toast.makeText(CadClienteTest.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                } else {
                    Toast.makeText(CadClienteTest.this, "Verifique os campos em vermelho.", Toast.LENGTH_SHORT).show();
                }
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
                                CidadeAdapter adapter = new CidadeAdapter(CadClienteTest.this, cidades);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spCidade.setAdapter(adapter);
                                spCidade.setClickable(true);
                                Drawable ativo = ContextCompat.getDrawable(CadClienteTest.this, R.drawable.borda1);
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

    private void setaDadosCampo() {
        Log.d("TST", "Caiu no seta dados");

        edNomeCompleto.setText(pessoaCarregada.getNome());
        edCPF.setText(pessoaCarregada.getCpf());
        edComplemento.setText(pessoaCarregada.getComplemento());
        edEmail.setText(pessoaCarregada.getEmail());
        edRua.setText(pessoaCarregada.getNmRua());
        edTelefone.setText(pessoaCarregada.getNrtelefone());
        edBairro.setText(pessoaCarregada.getBairro());
        edCEP.setText(pessoaCarregada.getCep());
        edNumero.setText(String.valueOf(pessoaCarregada.getNumero()));
        edRG.setText(pessoaCarregada.getRg());

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

    public boolean validaCampos() {

        boolean isErros = false;
        String nomeCompleto = edNomeCompleto.getText().toString().trim();
        if (nomeCompleto.isEmpty()) {
            edNomeCompleto.setError("Campo obrigatório");
            isErros = true;
        }

        String telefone = edTelefone.getText().toString().trim();
        if (telefone.isEmpty()) {
            edTelefone.setError("Campo obrigatório");
            isErros = true;

        }

        String cpf = edCPF.getText().toString().trim();
        if (cpf.isEmpty()) {
            edCPF.setError("Campo obrigatório");
            isErros = true;

        }

        String rg = edRG.getText().toString().trim();
        if (rg.isEmpty()) {
            edRG.setError("Campo obrigatório");
            isErros = true;

        }

        String rua = edRua.getText().toString().trim();
        if (rua.isEmpty()) {
            edRua.setError("Campo obrigatório");
            isErros = true;

        }

        String complemento = edComplemento.getText().toString().trim();

        String email = edEmail.getText().toString().trim();
        if (email.isEmpty()) {
            edEmail.setError("Campo obrigatório");
            isErros = true;

        }

        String cep = edCEP.getText().toString().trim();
        if (cep.isEmpty()) {
            edCEP.setError("Campo obrigatório");
            isErros = true;

        }

        String numero = edNumero.getText().toString().trim();
        if (numero.isEmpty()) {
            edNumero.setError("Campo obrigatório");
            isErros = true;

        }
/*
        // Validação para o Spinner de Estado
        if (spEstado.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) spEstado.getSelectedView();
            errorText.setError("Selecione um estado válido");
            isErros = true;

        }

        // Validação para o Spinner de Cidade
        if (spCidade.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) spCidade.getSelectedView();
            errorText.setError("Selecione uma cidade válida");
            isErros = true;

        }*/

        return isErros;
    }

    public void criarDentes(Cliente cliente) {
        for (int i = 1; i <= 32; i++) {
            Dentes dente = new Dentes();
            dente.setNrDente(i);
            dente.setDsDente("Dente " + i);
            //dente.setCliente(cliente);
        }
    }

    private void carregaDados(Long idPessoa) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPessoa apiPessoa = RetroFit.GET_PESSOA();

        Call<Pessoa> call = apiPessoa.GET_PESSOA("Bearer " + token, idPessoa);
        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful()) {

                    pessoaCarregada = response.body();
                    Log.d("TST", "Achou a pessoa e retornou + " + pessoaCarregada.getNome());
                    setaDadosCampo();

                } else {

                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {

            }
        });

    }
}
