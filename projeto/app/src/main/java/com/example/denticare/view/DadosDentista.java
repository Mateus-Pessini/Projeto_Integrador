package com.example.denticare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiEstado;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.util.DataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadosDentista extends AppCompatActivity {

    private Button btDdCancel;
    private Button btDdSalvar;
    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btCadClienteRecep;
    private LinearLayout btMeusDados;
    private TextView tvNome;
    private ImageView ivImgDentista;
    private EditText edNome, edFone, edMail, edCRO, edEspecialidade;

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
        tvNome = findViewById(R.id.tvNomeDadosPessoais);
        edNome = findViewById(R.id.edDentNomeComp);
        edFone = findViewById(R.id.edDentFone);
        edMail = findViewById(R.id.edDentMail);
        //edMail.setInputType(InputType.TYPE_NULL);
        edCRO = findViewById(R.id.edDentCro);
        edEspecialidade = findViewById(R.id.edDentEspecialidade);
        btMeusDados = findViewById(R.id.btMeusDados);
        ivImgDentista = findViewById(R.id.ivImgDentista);

        buscaTipoUsuario();

        String token = DataUtils.getToken(DadosDentista.this);
        ApiPessoa apiPessoa = RetroFit.REGISTER_PESSOA();

        Call<Pessoa> pessoaCall = apiPessoa.GET_PESSOA_BY_CPF(DataUtils.getDataFromTokenToShow(DadosDentista.this, "cpf"));
        pessoaCall.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful()) {
                    edNome.setText(response.body().getNome());
                    edFone.setText(response.body().getNrtelefone());
                    edMail.setText(response.body().getEmail());
                    edCRO.setText(response.body().getCro());
                    edEspecialidade.setText(response.body().getEspecialidade());

                    Toast.makeText(DadosDentista.this, "Dados Obtidos com Sucesso!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DadosDentista.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                    Log.e("", "Message =" + response.code());
                    Log.e("", "Body =" + response.body());
                    Log.e("", "ErroBody =" + response.errorBody());
                    Log.e("", "response =" + response);
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Toast.makeText(DadosDentista.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
            }
        });

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

                String token = DataUtils.getToken(DadosDentista.this);
                ApiPessoa apiPessoa = RetroFit.UPDATE_DADOS_DENTISTA();
                Pessoa pessoa = new Pessoa();
                pessoa.setEspecialidade(edEspecialidade.getText().toString());
                pessoa.setNome(edNome.getText().toString());
                pessoa.setCro(edCRO.getText().toString());
                pessoa.setNrtelefone(edFone.getText().toString());

                Long id = Long.parseLong(DataUtils.getDataFromTokenToShow(DadosDentista.this, "id"));

                Call<Pessoa> pessoaCall = apiPessoa.ALTERAR_DADOS_DENTISTA("Bearer " + token, pessoa, id);
                pessoaCall.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(DadosDentista.this, MainActivity.class);
                            startActivity(intent);
                            // Exibir uma mensagem de confirmação
                            Toast.makeText(DadosDentista.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(DadosDentista.this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                            Log.e("", "Message =" + response.code());
                            Log.e("", "Body =" + response.body());
                            Log.e("", "ErroBody =" + response.errorBody());
                            Log.e("", "response =" + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Toast.makeText(DadosDentista.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Consulta2.class);
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
                Intent intent = new Intent(DadosDentista.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosDentista.this, Clientes.class);
                startActivity(intent);
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
}