package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Dentista;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPreAgendamento {

    @Headers("Content-Type: application/json")
    @POST("pre-agendamento")
    Call<PreAgendamento> REGISTER_PRE_AGENDAMENTO(@Header("Authorization") String token, @Body PreAgendamento preAgendamento);
}
