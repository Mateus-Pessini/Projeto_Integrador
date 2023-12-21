package com.example.denticare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.Adapter.SelectionStrategy;
import com.example.denticare.R;
import com.example.denticare.api.Api.ApiDente;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiTratamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.Tratamentos.Tratamento;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Dentes;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DentesInfo extends AppCompatActivity {
    private CheckBox[] checkBoxes = new CheckBox[32]; // Seu array de CheckBoxes.
    private SelectionStrategy selectionStrategy;
    private LinearLayout btAgendarRecep, btSair, btMeusDados, btPdfRecep, btCadFotoRecep, btConsultaRecep, btCadClienteRecep;
    private EditText edDesc;
    private TextView tvNome;
    private ImageView ivImgDentista;
    private Button btCancel, btContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentes);

        NavigationUtil.hideNavigation(this);

        edDesc = findViewById(R.id.editTextDescricaoDentes);
        btCancel = findViewById(R.id.btn_cancel_selection);
        btContinue = findViewById(R.id.btn_confirm_selection);
        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        tvNome = findViewById(R.id.tvNome);
        ivImgDentista = findViewById(R.id.ivImgDentista);

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


        buscaTipoUsuario();

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, Consulta2.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, Clientes.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DentesInfo.this, DadosDentista.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < checkBoxes.length; i++) {
            String checkBoxId = "checkbox_dente_" + (i + 1);
            int resID = getResources().getIdentifier(checkBoxId, "id", getPackageName());
            checkBoxes[i] = findViewById(resID);
        }

        String edit = getIntent().getStringExtra("edit");

        if(edit != null && edit.equals("VISUALIZAR")){
            checkBoxes[0].setChecked(true);
            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i].setEnabled(false);
            }
            edDesc.setText("teste");
            edDesc.setEnabled(false);
        }else if(edit != null && edit.equals("EDIT")){
            checkBoxes[0].setChecked(true);
            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i].setEnabled(true);
            }
            edDesc.setText("teste");
            edDesc.setEnabled(false);
        }

        int denteSelecionado = getIntent().getIntExtra("denteSelecionado", -1);

        if (denteSelecionado != -1) {
            checkBoxes[denteSelecionado - 1].setChecked(true); // Ajuste o índice conforme necessário
        }

        String source = getIntent().getStringExtra("source");
        if (source != null) {
            switch (source) {
                case "ALL":
                    selectionStrategy = new AllTeethSelectionStrategy();
                    break;
                case "UPPER":
                    selectionStrategy = new UpperTeethSelectionStrategy();
                    break;
                case "LOWER":
                    selectionStrategy = new LowerTeethSelectionStrategy();
                    break;
            }
        }


        // Aplica a estratégia de seleção
        if (selectionStrategy != null) {
            selectionStrategy.select(checkBoxes);
        }

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action = getIntent().getStringExtra("edit");
                if ("EDIT".equals(action) || "VISUALIZAR".equals(action)) {
                    Intent intent = new Intent(DentesInfo.this, HistoricoDentario.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DentesInfo.this, EscolhaDente.class);
                    startActivity(intent);
                }
            }
        });

    }


    public class AllTeethSelectionStrategy implements SelectionStrategy {
        @Override
        public void select(CheckBox[] checkBoxes) {
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setChecked(true);
            }
        }
    }

    public class UpperTeethSelectionStrategy implements SelectionStrategy {
        @Override
        public void select(CheckBox[] checkBoxes) {
            // Assume that the first half of the array represents upper teeth
            for (int i = 0; i < checkBoxes.length / 2; i++) {
                checkBoxes[i].setChecked(true);
            }
        }
    }

    public class LowerTeethSelectionStrategy implements SelectionStrategy {
        @Override
        public void select(CheckBox[] checkBoxes) {
            // Assume that the second half of the array represents lower teeth
            for (int i = checkBoxes.length / 2; i < checkBoxes.length; i++) {
                checkBoxes[i].setChecked(true);
            }
        }
    }

    public void onConfirmSelectionClicked(View view) {
        List<Integer> selectedTeeth = new ArrayList<>();
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                selectedTeeth.add(i + 1); // Adicione 1 se sua contagem começar do dente 1.
            }
        }

        // Agora você tem uma lista dos dentes selecionados para enviar ao backend.
        sendSelectionToBackend(selectedTeeth);
    }

    public void sendSelectionToBackend(List<Integer> selectedTeeth) {
        ApiPessoa apiPessoa = RetroFit.GET_PESSOA();
        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        Call<Pessoa> pessoaCall = apiPessoa.GET_PESSOA("Bearer " + token, 99L);

        pessoaCall.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful()) {
                    Pessoa cli = response.body();
                    createAndSendTratamento(cli, selectedTeeth, token);
                } else {
                    handleErrorPessoa(response);
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                // Tratamento de falha ao buscar a pessoa
            }
        });
    }

    private void createAndSendTratamento(Pessoa cli, List<Integer> selectedTeeth, String token) {
        ApiTratamento apiTratamento = RetroFit.REGISTER_TRATAMENTO();
        List<Dentes> dentesList = new ArrayList<>();
        for (Integer toothNumber : selectedTeeth) {
            Dentes dente = new Dentes();
            dente.setNrDente(toothNumber);
            dente.setDsDente(edDesc.getText().toString());
            dente.setCliente(cli); // Use o objeto 'cli' que foi obtido com sucesso
            dentesList.add(dente);
        }

        Tratamento trat = new Tratamento();
        trat.setDentesId(dentesList);
        trat.setDs_observacao(edDesc.getText().toString());
        trat.setCliente(cli);
        Log.d("TratamentoDebug", "DentesList: " + dentesList.toString());
        Log.d("TratamentoDebug", "Descrição: " + edDesc.getText().toString());
        if (cli != null) {
            Log.d("TratamentoDebug", "Cliente ID: " + cli.getId());
        } else {
            Log.e("TratamentoDebug", "Objeto 'cli' é null");
        }

        // Agora faça a chamada de API para enviar o objeto Tratamento
        Call<Tratamento> call = apiTratamento.REGISTER_TRATAMENTO("Bearer " + token, trat);
        call.enqueue(new Callback<Tratamento>() {
            @Override
            public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                if (response.isSuccessful()) {
                    String action = getIntent().getStringExtra("edit");
                    if ("EDIT".equals(action) || "VISUALIZAR".equals(action)){
                        Toast.makeText(DentesInfo.this, "Tratamento editado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DentesInfo.this, HistoricoDentario.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(DentesInfo.this, "Tratamento cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DentesInfo.this, Consulta2.class);
                        startActivity(intent);
                    }

                } else {
                    handleError(response);
                }
            }

            @Override
            public void onFailure(Call<Tratamento> call, Throwable t) {
                Toast.makeText(DentesInfo.this, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleError(Response<Tratamento> response) {
        try {
            // Tentar converter o corpo do erro em uma String
            String errorBody = response.errorBody().string();
            Log.e("Error Body", errorBody);

            // Tentar analisar o JSON da mensagem de erro
            JSONObject jsonError = new JSONObject(errorBody);
            JSONArray errorArray = jsonError.getJSONArray("error");
            if (errorArray.length() > 0) {
                // Obter a primeira mensagem de erro
                String errorMessage = errorArray.getString(0);

                // Exibir a mensagem de erro
                Toast.makeText(DentesInfo.this, errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                Log.e("Error Body", "Formato JSON de erro inesperado.");
                Toast.makeText(DentesInfo.this, "Erro inesperado ao processar a resposta do servidor.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException | IOException e) {
            // Lidar com erros de conversão
            e.printStackTrace();
            Toast.makeText(DentesInfo.this, "Erro ao processar a resposta do servidor.", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleErrorPessoa(Response<Pessoa> response) {
        try {
            // Tentar converter o corpo do erro em uma String
            String errorBody = response.errorBody().string();
            Log.e("Error Body", errorBody);

            // Tentar analisar o JSON da mensagem de erro
            JSONObject jsonError = new JSONObject(errorBody);
            JSONArray errorArray = jsonError.getJSONArray("error");
            if (errorArray.length() > 0) {
                // Obter a primeira mensagem de erro
                String errorMessage = errorArray.getString(0);

                // Exibir a mensagem de erro
                Toast.makeText(DentesInfo.this, errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                Log.e("Error Body", "Formato JSON de erro inesperado.");
                Toast.makeText(DentesInfo.this, "Erro inesperado ao processar a resposta do servidor.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException | IOException e) {
            // Lidar com erros de conversão
            e.printStackTrace();
            Toast.makeText(DentesInfo.this, "Erro ao processar a resposta do servidor.", Toast.LENGTH_SHORT).show();
        }
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

}
