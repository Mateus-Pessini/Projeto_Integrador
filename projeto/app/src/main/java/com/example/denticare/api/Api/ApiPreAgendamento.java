package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPreAgendamento {

    @Headers("Content-Type: application/json")
    @POST("pre-agendamento")
    Call<PreAgendamento> REGISTER_PRE_AGENDAMENTO(@Body PreAgendamento preAgendamento);

    @GET("pre-agendamento")
    Call<List<PreAgendamento>> GET_ALL_PRE_AGENDAMENTO(@Header("Authorization") String authorization);

    @DELETE("pre-agendamento/{id}")
    Call<Void> DELETE_PRE_AGENDAMENTO(@Header("Authorization") String authorization, @Path("id") Long itemId);

    @PUT("pre-agendamento/{edit}")
    Call<PreAgendamento> EDITAR_PRE_AGENDAMENTO(@Header("Authorization") String authorization, @Body PreAgendamento agendamento);

}
