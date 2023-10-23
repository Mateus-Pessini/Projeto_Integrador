package com.example.denticare.cadastro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.denticare.Adapter.CidadeAdapter;
import com.example.denticare.Adapter.EstadoAdapter;
import com.example.denticare.Adapter.PaisAdapter;
import com.example.denticare.DadosDentista;
import com.example.denticare.GeraPDF;
import com.example.denticare.MainActivity;
import com.example.denticare.NavigationUtil;
import com.example.denticare.R;
import com.example.denticare.SelClienteFoto;
import com.example.denticare.agendamento.Agenda;
import com.example.denticare.agendamento.Consulta;
import com.example.denticare.api.Api.ApiCidade;
import com.example.denticare.api.Api.ApiCliente;
import com.example.denticare.api.Api.ApiEstado;
import com.example.denticare.api.Api.ApiPais;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.Cidade;
import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Endereco;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.Pais;
import com.example.denticare.opcoes.OpcaoCadUsuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadCliente extends AppCompatActivity {

    private LinearLayout btAgendarRecep, btSair, btMeusDados, btPdfRecep, btCadFotoRecep, btConsultaRecep, btCadClienteRecep;
    private Button btCancel, btSalvar;

    private EditText edNomeCompleto, edTelefone, edCPF, edRG, edRua, edComplemento, edCEP, edNumero, edEmail, edBairro;

    private Spinner spPais, spEstado, spCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadcliente);

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

        spPais = findViewById(R.id.spinnerPais);
        spEstado = findViewById(R.id.spinnerEstado);
        spCidade = findViewById(R.id.spinnerCidade);

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, Consulta.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, Login.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcaoCadUsuario.showCustomDialog(CadCliente.this, new OpcaoCadUsuario.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCliente.this, MainActivity.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(CadCliente.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCliente apiCliente = RetroFit.REGISTER_CLIENTE();
                ApiPais apiPais = RetroFit.GET_ALL();
                validaCampos();
                Endereco end = new Endereco();
                Cliente cli = new Cliente();
                end.setCEP(edCEP.getText().toString());
                end.setCidade((Cidade) spCidade.getSelectedItem());
                end.setComplemento(edComplemento.getText().toString());
                end.setNmRua(edRua.getText().toString());
                end.setNumero(Integer.parseInt(edNumero.getText().toString()));
                end.setBairro(edBairro.getText().toString());
                cli.setCPF(edCPF.getText().toString());
                cli.setRG(edRG.getText().toString());
                cli.setEmail(edEmail.getText().toString());
                cli.setNome(edNomeCompleto.getText().toString());
                cli.setNrtelefone(edTelefone.getText().toString());
                cli.getEnderecos().add(end);

                SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");

                Call<List<Pais>> paisCall = apiPais.GET_ALL_PAIS("Bearer" + token);
                paisCall.enqueue(new Callback<List<Pais>>() {
                    @Override
                    public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                        List<Pais> listaPaises = response.body();
                        PaisAdapter paisAdapter = new PaisAdapter(CadCliente.this, listaPaises);
                        paisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spPais.setAdapter(paisAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Pais>> call, Throwable t) {
                        Toast.makeText(CadCliente.this, "Erro ao buscar paises", Toast.LENGTH_SHORT).show();
                    }
                });


                Call<Cliente> clienteCall = apiCliente.REGISTER_CLIENTE("Bearer" + token, cli);
                clienteCall.enqueue(new Callback<Cliente>() {
                    @Override
                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CadCliente.this, "Cliente cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                            limparCampos();
                            //Intent intent = new Intent(CadCliente.this, MainActivity.class);
                            //startActivity(intent);
                        } else {
                            Toast.makeText(CadCliente.this, "Não foi possível salvar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Cliente> call, Throwable t) {
                        Toast.makeText(CadCliente.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Long paisId = ((Pais) spPais.getSelectedItem()).getId();
                SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                if (!token.isEmpty()) {
                    ApiEstado apiEstado = RetroFit.GET_ALL_BY_PAIS();
                    Call<List<Estado>> call = apiEstado.GET_ALL_BY_PAIS(token, paisId);
                    call.enqueue(new Callback<List<Estado>>() {
                        @Override
                        public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                            if (response.isSuccessful()) {
                                List<Estado> estados = response.body();
                                EstadoAdapter estadoAdapter = new EstadoAdapter(CadCliente.this, estados);
                                estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spEstado.setAdapter(estadoAdapter);
                                spEstado.setClickable(true);
                                Drawable ativo = ContextCompat.getDrawable(CadCliente.this, R.drawable.borda1);
                                spEstado.setBackground(ativo);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Estado>> call, Throwable t) {
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

        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Long estadoId = ((Estado) spEstado.getSelectedItem()).getId(); // Supondo que seu objeto Estado tenha um método getId() para obter o ID
                SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                if (!token.isEmpty()) {
                    ApiCidade apiCidade = RetroFit.GET_ALL_BY_ESTADO();
                    Call<List<Cidade>> call = apiCidade.GET_ALL_BY_ESTADO(token,estadoId);
                    call.enqueue(new Callback<List<Cidade>>() {
                        @Override
                        public void onResponse(Call<List<Cidade>>call, Response<List<Cidade>> response) {
                            if (response.isSuccessful()) {
                                List<Cidade> cidades = response.body();
                                CidadeAdapter adapter = new CidadeAdapter(CadCliente.this, cidades);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spCidade.setAdapter(adapter);
                                spCidade.setClickable(true);
                                Drawable ativo = ContextCompat.getDrawable(CadCliente.this, R.drawable.borda1);
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
        spPais.setSelection(0);
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

        // Validação para o Spinner de País
        if (spPais.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) spPais.getSelectedView();
            errorText.setError("Selecione um país válido");
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


}