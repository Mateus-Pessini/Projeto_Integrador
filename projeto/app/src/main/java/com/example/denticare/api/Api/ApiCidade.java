package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Cidade;

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

public interface ApiCidade {

    @Headers("Content-Type: application/json")
    @POST("cidade")
    Call<Cidade> REGISTER_CIDADE(@Header("Authorization") String token, @Body Cidade cidade);

    @GET("cidade")
    Call<List<Cidade>> GET_ALL_CIDADE(@Header("Authorization") String authorization);

    @GET("cidade/{id}")
    Call<Cidade> GET_CIDADE(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("cidade/{id}")
    Call<Cidade> DELETAR_CIDADE(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("cidade")
    Call<Cidade> ALTERAR_CIDADE(@Header("Authorization") String token, @Body Cidade cidade);

    @GET("cidade/estado/{estadoId}")
    Call<List<Cidade>> GET_ALL_BY_ESTADO(@Header("Authorization") String authorization, @Path("estadoId") Long estadoId);

    @GET("cidade/estado/{estadoId}")
    Call<List<Cidade>> GET_ALL_BY_ESTADO_WITHOUT_AUTH(@Path("estadoId") Long estadoId);

}
