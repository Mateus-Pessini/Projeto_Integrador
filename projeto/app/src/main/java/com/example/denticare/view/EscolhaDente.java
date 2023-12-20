package com.example.denticare.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.denticare.R;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Base64;

public class EscolhaDente extends AppCompatActivity {

    private Button btVoltar, btTodos, btSuperior, btInferior;

    private TextView tvNome;

    private EditText edDente, edDescricao;
    private ImageView ivImgDentista;
    private LinearLayout btAgendarRecep, btSair, btMeusDados, btPdfRecep, btCadFotoRecep, btConsultaRecep, btCadClienteRecep;


    private View.OnClickListener denteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // A tag nos diz qual dente foi clicado
            Integer denteTag = Integer.valueOf((String) view.getTag());
            abrirChecklistComDenteSelecionado(denteTag);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_dente);
        NavigationUtil.hideNavigation(this);

        btSuperior = findViewById(R.id.btSuperiorDentes);
        btTodos = findViewById(R.id.btTodosDentes);
        btInferior = findViewById(R.id.btInferiorDentes);
        btVoltar = findViewById(R.id.btVoltar);
        tvNome = findViewById(R.id.tvNome);
        ivImgDentista = findViewById(R.id.ivImgDentista);
        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);

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
        }

        for (int i = 1; i <= 32; i++) { // Ajuste os números de acordo com seus IDs e tags
            String imageViewId = "dente" + i;
            int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            ImageView imageView = findViewById(resID);
            imageView.setOnClickListener(denteClickListener);
        }

        //Adicionado fixo para a pessoa 3.
        Intent myIntent = getIntent();
        String pessoaSelecionada = myIntent.getStringExtra("pessoa");


        btSuperior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolhaDente.this, DentesInfo.class);
                intent.putExtra("source", "UPPER"); // Use "ALL", "UPPER", ou "LOWER" dependendo do contexto.
                startActivity(intent);
            }
        });

        btInferior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolhaDente.this, DentesInfo.class);
                intent.putExtra("source", "LOWER"); // Use "ALL", "UPPER", ou "LOWER" dependendo do contexto.
                startActivity(intent);
            }
        });

        btTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolhaDente.this, DentesInfo.class);
                intent.putExtra("source", "ALL"); // Use "ALL", "UPPER", ou "LOWER" dependendo do contexto.
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaDente.this, InicialConsulta.class);
                startActivity(intent);
            }
        });

    }

    private void abrirChecklistComDenteSelecionado(int denteTag) {
        Intent intent = new Intent(EscolhaDente.this, DentesInfo.class);
        intent.putExtra("denteSelecionado", denteTag);
        startActivity(intent);
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