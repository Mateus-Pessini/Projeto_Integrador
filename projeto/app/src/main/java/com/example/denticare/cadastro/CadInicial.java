package com.example.denticare.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denticare.MainActivity;
import com.example.denticare.NavigationUtil;
import com.example.denticare.R;
import com.example.denticare.api.Api.ApiError;
import com.example.denticare.api.Api.ApiErrorParser;
import com.example.denticare.api.Api.ApiUser;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.user.RegisterDTO;
import com.example.denticare.api.models.user.UsuarioRole;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CadInicial extends AppCompatActivity {

    private TextView tvLogin;
    private Button btLogar1;
    private RadioGroup btRadioGroup;
    private EditText edCadEmail;
    private TextInputEditText edCriarSenha;
    private RadioButton rbDentista;
    private RadioButton rbRecepcionista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadinicial);

        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        tvLogin = findViewById(R.id.tvLogin);
        btLogar1 = findViewById(R.id.btLogar1);
        btRadioGroup = findViewById(R.id.btRadioGroup);
        edCadEmail = findViewById(R.id.edCadEmail);
        edCriarSenha = findViewById(R.id.edCriarSenha);
        rbDentista = findViewById(R.id.rbDentista);
        rbRecepcionista = findViewById(R.id.rbRecepcionista);

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
                String login = edCadEmail.getText().toString();
                String senha = edCriarSenha.getText().toString();
                UsuarioRole role = UsuarioRole.DENTISTA;
                if (rbDentista.isChecked()) {
                    role = UsuarioRole.DENTISTA;
                } else if (rbRecepcionista.isChecked()) {
                    role = UsuarioRole.SECRETARIA;
                }
                RegisterDTO register = new RegisterDTO(login, senha, role);


                ApiUser apiUser = RetroFit.REGISTER_USER();

                Call<Void> call = apiUser.REGISTER_USER(register);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {

                            Toast.makeText(CadInicial.this, "Cadastrado com sucesso!.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CadInicial.this, MainActivity.class);
                            startActivity(intent);

                        }else {
                        try {
                            ApiError apiError = ApiErrorParser.parseError(response.errorBody().string());
                            if (apiError != null) {
                                List<String> errorMessages = apiError.getErrorMessages();
                                String errorMessage = errorMessages.get(0);
                                Toast.makeText(CadInicial.this, errorMessage, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CadInicial.this, "Erro inesperado! Contate o suporte.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(CadInicial.this, "Verifique a conexão com servidor!.", Toast.LENGTH_SHORT).show();


                    }
                });
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