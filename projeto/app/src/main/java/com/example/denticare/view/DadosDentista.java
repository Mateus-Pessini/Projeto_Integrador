package com.example.denticare.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiDentista;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.Dentista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadosDentista extends AppCompatActivity {

    private Button btDdCancel, btDdSalvar, btFotoDentista;
    private EditText edNome, edEmail, edTelefone, edCro, edEspecialidade;
    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btCadClienteRecep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_dentista);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        btDdCancel = findViewById(R.id.btDdCancel);
        btDdSalvar = findViewById(R.id.btDdSalvar);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        edCro = findViewById(R.id.editTextCro);
        edEmail = findViewById(R.id.editTextEmailDentista);
        edEspecialidade = findViewById(R.id.editTextEspecialidade);
        edNome = findViewById(R.id.editTextNomeDentista);
        edTelefone = findViewById(R.id.editTextTelefoneDentista);
        btFotoDentista = findViewById(R.id.buttonAddFoto);

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        btDdCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, MainActivity.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(DadosDentista.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });

        btDdSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiDentista apiDentista = RetroFit.REGISTER_DENTISTA();
                validaCampos();
                Dentista dentista = new Dentista();
                dentista.setCRO(edCro.getText().toString());
                dentista.setEspecialidade(edEspecialidade.getText().toString());
                dentista.setEmail(edEmail.getText().toString());
                dentista.setNome(edNome.getText().toString());
                dentista.setNrtelefone(edTelefone.getText().toString());

                Call<Dentista> dentistaCall = apiDentista.REGISTER_DENTISTA("Bearer " + token, dentista);
                dentistaCall.enqueue(new Callback<Dentista>() {
                    @Override
                    public void onResponse(Call<Dentista> call, Response<Dentista> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DadosDentista.this, "Dentista cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                            limparCampos();
                        } else {
                            Toast.makeText(DadosDentista.this, "Não foi possivel cadastrar o Dentista!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Dentista> call, Throwable t) {

                    }
                });
                // Exibir uma mensagem de confirmação
                Toast.makeText(DadosDentista.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Consulta.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Login.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcaoCadUsuario.showCustomDialog(DadosDentista.this, new OpcaoCadUsuario.CustomDialogListener() {
                    @Override
                    public void onNegativeButtonClick() {

                    }
                });
            }
        });

    }

    public void validaCampos() {
        String nomeCompleto = edNome.getText().toString().trim();
        if (nomeCompleto.isEmpty()) {
            edNome.setError("Campo obrigatório");
        } else if (nomeCompleto.length() < 3 || nomeCompleto.length() > 100) {
            edNome.setError("Nome deve ter no minimo 3 e no máximo 100 digitos");
        }

        String CRO = edCro.getText().toString().trim();
        if (CRO.isEmpty()) {
            edCro.setError("Campo obrigatório");
        } else if (CRO.length() < 3 || CRO.length() > 25) {
            edCro.setError("Cro deve ter no minimo 3 e no máximo 25 digitos");
        }

        String especialidade = edEspecialidade.getText().toString().trim();
        if (especialidade.isEmpty()) {
            edEspecialidade.setError("Campo obrigatório");
        } else if (especialidade.length() < 3 || especialidade.length() > 100) {
            edEspecialidade.setError("Especialidade deve ter no minimo 3 e no máximo 100 digitos");
        }

        String telefone = edTelefone.getText().toString().trim();
        if (telefone.isEmpty()) {
            edTelefone.setError("Campo obrigatório");
        } else if (telefone.length() < 11 || telefone.length() > 11) {
            edTelefone.setError("Telefone deve ter 11 digitos");
        }

        String email = edEmail.getText().toString().trim();
        if (email.isEmpty()) {
            edEmail.setError("Campo obrigatório");
        } else if (email.length() < 3 || email.length() > 100) {
            edEmail.setError("Email deve ter no minimo 3 e no máximo 100 digitos");
        }
    }

    public void limparCampos() {
        edTelefone.setText("");
        edEmail.setText("");
        edNome.setText("");
        edCro.setText("");
        edEspecialidade.setText("");

        edTelefone.setError(null);
        edEmail.setError(null);
        edNome.setError(null);
        edCro.setError(null);
        edEspecialidade.setError(null);
    }
}