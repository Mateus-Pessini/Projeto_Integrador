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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.Adapter.SelectionStrategy;
import com.example.denticare.R;
import com.example.denticare.api.Api.ApiDente;
import com.example.denticare.api.Api.ApiTratamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.Tratamentos.Tratamento;
import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Dentes;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DentesInfo extends AppCompatActivity {
    private CheckBox[] checkBoxes = new CheckBox[32]; // Seu array de CheckBoxes.
    private SelectionStrategy selectionStrategy;

    private EditText edDesc;
    private Button btCancel, btContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentes);

        edDesc = findViewById(R.id.editTextDescricaoDentes);
        btCancel = findViewById(R.id.btn_cancel_selection);
        btContinue = findViewById(R.id.btn_confirm_selection);

        for (int i = 0; i < checkBoxes.length; i++) {
            String checkBoxId = "checkbox_dente_" + (i + 1);
            int resID = getResources().getIdentifier(checkBoxId, "id", getPackageName());
            checkBoxes[i] = findViewById(resID);
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
                Intent intent = new Intent(DentesInfo.this, EscolhaDente.class);
                startActivity(intent);
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
        // Suponha que você já tenha uma instância de Retrofit configurada
        ApiTratamento apiTratamento = RetroFit.REGISTER_TRATAMENTO();
        Pessoa cli = new Pessoa();
        cli.setNome("Mateus Pessini Scherer");
        cli.setId(99L);
        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("", "" + token);
        // Você precisará criar uma lista de objetos Dentes com base nos números selecionados
        List<Dentes> dentesList = new ArrayList<>();
        for (Integer toothNumber : selectedTeeth) {
            Dentes dente = new Dentes();
            dente.setNrDente(toothNumber);
            dente.setDsDente(edDesc.getText().toString());
            dente.setCliente(cli);
            // Configure qualquer outra propriedade necessária de Dentes
            dentesList.add(dente);
        }

        Tratamento tratamento = new Tratamento();
        tratamento.setDentesId(dentesList);
        tratamento.setDs_observacao(edDesc.getText().toString());
        tratamento.setCliente(99L);

        // Agora faça a chamada de API para enviar o objeto Tratamento
        Call<Tratamento> call = apiTratamento.REGISTER_TRATAMENTO("Bearer " + token, tratamento);
        call.enqueue(new Callback<Tratamento>() {
            @Override
            public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DentesInfo.this, "Tratamento cadastrado com sucesso", Toast.LENGTH_SHORT).show();
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

}
