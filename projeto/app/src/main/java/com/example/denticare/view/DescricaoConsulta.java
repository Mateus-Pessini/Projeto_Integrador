package com.example.denticare.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiTratamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.Tratamentos.Tratamento;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.util.DataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescricaoConsulta {

    public static void showCustomDialog(Context context, final DescricaoConsulta.CustomDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_descricao_consulta, null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        Button btCancel = view.findViewById(R.id.btCancel);
        Button btSalvar = view.findViewById(R.id.btSalvar);
        EditText edDente = view.findViewById(R.id.edDescDentes);
        EditText edDescricao = view.findViewById(R.id.edDescricaoDentes);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InicialConsulta.class);
                intent.putExtra("pessoa","3");
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = DataUtils.getToken(context);
                ApiTratamento apiTratamento = RetroFit.REGISTER_TRATAMENTO();

                Tratamento trat = new Tratamento();
                trat.setId(null);
                trat.setConsultaId(null);
                trat.setDentesId(null);
                trat.setCliente(3L);
                trat.setDs_observacao(edDente.getText().toString() + " - " + edDescricao.getText().toString());

                Call<Tratamento> tratamentoCall = apiTratamento.REGISTER_TRATAMENTO(token, trat);

                tratamentoCall.enqueue(new Callback<Tratamento>() {
                    @Override
                    public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Descrição do Tratamento salvo com Sucesso!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, InicialConsulta.class);
                            intent.putExtra("pessoa","3");
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(context, "Não foi possível salvar.", Toast.LENGTH_SHORT).show();
                            Log.e("", "Message =" + response.code());
                            Log.e("", "Body =" + response.body());
                            Log.e("", "ErroBody =" + response.errorBody());
                            Log.e("", "response =" + response);
                        }

                    }

                    @Override
                    public void onFailure(Call<Tratamento> call, Throwable t) {
                        Toast.makeText(context, "Falha com o Servidor!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        alertDialog.show();
    }

    public interface CustomDialogListener {
        void onNegativeButtonClick();
    }


}