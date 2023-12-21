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
import retrofit2.http.Query;

public interface ApiPessoa {

    @Headers("Content-Type: application/json")
    @POST("pessoa")
    Call<Pessoa> REGISTER_PESSOA(@Header("Authorization") String token, @Body Pessoa pessoa);

    @Headers("Content-Type: application/json")
    @POST("pessoa")
    Call<Pessoa> REGISTER_PESSOA_WITHOUT_AUTH(@Body Pessoa pessoa);

    @Headers("Content-Type: application/json")
    @PUT("pessoa")
    Call<Pessoa> PUT_PESSOA(@Header("Authorization") String authorization, @Body Pessoa pessoa);

    @Headers("Content-Type: application/json")
    @PUT("pessoa/cliente")
    Call<Pessoa> PUT_PESSOA_CLIENTE(@Header("Authorization") String authorization, @Body Pessoa pessoa);
    @GET("pessoa")
    Call<List<Pessoa>> GET_ALL_PESSOA(@Header("Authorization") String authorization);

    @GET("find/cliente/{nome}")
    Call<List<Pessoa>> FIND_CLIENTES(@Header("Authorization") String authorization, @Query("nome") String nome);

    @GET("pessoa/{id}")
    Call<Pessoa> GET_PESSOA(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("pessoa/find/{cpf}")
    Call<Pessoa> GET_PESSOA_BY_CPF(@Path("cpf") String cpf);

    @DELETE("pessoa/{id}")
    Call<Void> DELETAR_PESSOA(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("pessoa")
    Call<Pessoa> ALTERAR_PESSOA(@Header("Authorization") String token, @Body Pessoa pessoa);

    @Headers("Content-Type: application/json")
    @PUT("pessoa/{id}")
    Call<Pessoa> ALTERAR_DADOS_DENTISTA(@Header("Authorization") String token, @Body Pessoa pessoa, @Path("id") Long id);

    @GET("pessoa/pessoa-cliente")
    Call<List<Pessoa>> GET_PESSOA_CLIENTE(@Header("Authorization") String authorization);
}
