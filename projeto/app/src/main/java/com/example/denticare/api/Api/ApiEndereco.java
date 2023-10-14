package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Endereco;

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

public interface ApiEndereco {

    @Headers("Content-Type: application/json")
    @POST("Endereco")
    Call<Endereco> REGISTER_ENDERECO(@Header("Authorization") String token, @Body Endereco endereco);

    @GET("Endereco")
    Call<List<Endereco>> GET_ALL_ENDERECO(@Header("Authorization") String authorization);

    @GET("Endereco/{id}")
    Call<Endereco> GET_ENDERECO(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("Endereco/{id}")
    Call<Endereco> DELETAR_ENDERECO(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("Endereco")
    Call<Endereco> ALTERAR_ENDERECO(@Header("Authorization") String token, @Body Endereco endereco);
}
