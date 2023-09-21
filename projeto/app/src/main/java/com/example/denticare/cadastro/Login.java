package com.example.denticare.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.denticare.MainActivity;
import com.example.denticare.NavigationUtil;
import com.example.denticare.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private TextView tvCadastrar;
    private Button btLogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        tvCadastrar = findViewById(R.id.tvCadastrar);
        btLogar =findViewById(R.id.btLogar);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CadInicial.class);
                startActivity(intent);
            }
        });

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextInputLayout textInputLayoutSenha = findViewById(R.id.textInputLayoutSenha);
        TextInputEditText edtSenha = findViewById(R.id.edCriarSenha);

        edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textInputLayoutSenha.setEndIconDrawable(R.drawable.iconeinvisivel);

        textInputLayoutSenha.setHintEnabled(false);

        textInputLayoutSenha.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputType = edtSenha.getInputType();

                if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {

                    edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    textInputLayoutSenha.setEndIconDrawable(R.drawable.iconevisivel);
                } else {

                    edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    textInputLayoutSenha.setEndIconDrawable(R.drawable.iconeinvisivel);
                }

                edtSenha.setSelection(edtSenha.getText().length());

            }
        });

    }
}