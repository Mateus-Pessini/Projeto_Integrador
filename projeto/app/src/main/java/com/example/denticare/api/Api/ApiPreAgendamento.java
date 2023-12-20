package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPreAgendamento {

    @Headers("Content-Type: application/json")
    @POST("pre-agendamento")
    Call<PreAgendamento> REGISTER_PRE_AGENDAMENTO(@Body PreAgendamento preAgendamento);

    @GET("pre-agendamento")
    Call<List<ConsultaList>> GET_ALL_PRE_AGENDAMENTO(@Header("Authorization") String authorization);

}
