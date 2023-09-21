package com.example.denticare.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.denticare.MainActivity;
import com.example.denticare.NavigationUtil;
import com.example.denticare.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadInicial extends AppCompatActivity {

    private TextView tvLogin;
    private Button btLogar1;
    private RadioGroup btRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadinicial);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        tvLogin = findViewById(R.id.tvLogin);
        btLogar1 = findViewById(R.id.btLogar1);
        btRadioGroup = findViewById(R.id.btRadioGroup);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadInicial.this, Login.class);
                startActivity(intent);
            }
        });

        btLogar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadInicial.this, MainActivity.class);
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