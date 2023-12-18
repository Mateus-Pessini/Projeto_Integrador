package com.example.denticare.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.denticare.R;

public class EscolhaDente extends AppCompatActivity {

    private Button btVoltar, btTodos, btSuperior, btInferior;

    private EditText edDente, edDescricao;


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
                Intent intent = new Intent(EscolhaDente.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void abrirChecklistComDenteSelecionado(int denteTag) {
        Intent intent = new Intent(EscolhaDente.this, DentesInfo.class);
        intent.putExtra("denteSelecionado", denteTag);
        startActivity(intent);
    }
}