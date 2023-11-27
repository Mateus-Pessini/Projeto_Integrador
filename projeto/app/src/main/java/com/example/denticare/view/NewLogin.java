package com.example.denticare.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiError;
import com.example.denticare.api.Api.ApiErrorParser;
import com.example.denticare.api.Api.ApiUser;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.user.AuthenticationDTO;
import com.example.denticare.api.models.user.LoginResponseDTO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewLogin extends AppCompatActivity {

    private TextView tvCadastrar;
    private TextView tvCadastrarPreAgendamento;
    private Button btLogar;
    private EditText edtLogin;
    private EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);


        NavigationUtil.hideNavigation(this);

        tvCadastrar = findViewById(R.id.tvCadastrar2);
        tvCadastrarPreAgendamento = findViewById(R.id.tvCadastrarPreAgendamento);
        btLogar =findViewById(R.id.btLogar);
        edtLogin = findViewById(R.id.edLogin);
        edtSenha = findViewById(R.id.edCriarSenha);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewLogin.this, CadUsuario.class);
                startActivity(intent);
            }
        });

        tvCadastrarPreAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewLogin.this, CadastroPreAgendamento.class);
                startActivity(intent);
            }
        });

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                boolean isLoginValid = validateAndSetError(edtLogin);
                boolean isSenhaValid = validateAndSetError(edtSenha);
                if (!senha.isEmpty()) {

                    edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);



                }

                if (isLoginValid && isSenhaValid) {
                    ApiUser apiUser = RetroFit.LOGIN_CALL();
                    AuthenticationDTO authenticationDTO = new AuthenticationDTO(login, senha);

                    Call<LoginResponseDTO> call = apiUser.LOGIN_CALL(authenticationDTO);
                    call.enqueue(new Callback<LoginResponseDTO>() {
                        @Override
                        public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                            if (response.isSuccessful()) {
                                LoginResponseDTO loginResponseDTO = response.body();
                                String token = loginResponseDTO.getToken();
                                //String role = UsuarioRole.DENTISTA.getRole();



                                SharedPreferences sharedPreferences = getSharedPreferences("MyToken", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", token);
                                //editor.putString("role", role);
                                editor.apply();
                                Log.e("",""+token);

                                Toast.makeText(NewLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(NewLogin.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                try {
                                    ApiError apiError = ApiErrorParser.parseError(response.errorBody().string());
                                    if (apiError != null) {
                                        List<String> errorMessages = apiError.getErrorMessages();
                                        String errorMessage = errorMessages.get(0);
                                        Toast.makeText(NewLogin.this, errorMessage, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(NewLogin.this, "Erro inesperado! Contate o suporte.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                            Log.e("erro " , t.getMessage());
                            Toast.makeText(NewLogin.this, "Sem conexão com servidor!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

    private boolean validateAndSetError(EditText editText) {
        String text = editText.getText().toString().trim();

        if (text.isEmpty()) {
            editText.setError("Campo obrigatório");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }
}