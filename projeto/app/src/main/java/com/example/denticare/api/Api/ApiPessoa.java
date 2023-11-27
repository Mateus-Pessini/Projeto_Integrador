package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Pessoa;

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

public interface ApiPessoa {

    @Headers("Content-Type: application/json")
    @POST("pessoa")
    Call<Pessoa> REGISTER_PESSOA(@Header("Authorization") String token, @Body Pessoa pessoa);

    @GET("pessoa")
    Call<List<Pessoa>> GET_ALL_PESSOA(@Header("Authorization") String authorization);

    @GET("pessoa/{id}")
    Call<Pessoa> GET_PESSOA(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("pessoa/cpf/{cpf}")
    Call<Pessoa> GET_PESSOA_BY_CPF(@Header("Authorization") String authorization, @Path("cpf") String cpf);

    @DELETE("pessoa/{id}")
    Call<Pessoa> DELETAR_PESSOA(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("pessoa")
    Call<Pessoa> ALTERAR_PESSOA(@Header("Authorization") String token, @Body Pessoa pessoa);
}
