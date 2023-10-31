package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Cliente;

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

public interface ApiCliente {

    @Headers("Content-Type: application/json")
    @POST("cliente")
    Call<Cliente> REGISTER_CLIENTE(@Header("Authorization") String token, @Body Cliente cliente);

    @GET("cliente")
    Call<List<Cliente>> GET_ALL_CLIENTE(@Header("Authorization") String authorization);

    @GET("cliente/{id}")
    Call<Cliente> GET_CLIENTE(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("cliente/{id}")
    Call<Cliente> DELETAR_CLIENTE(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("cliente")
    Call<Cliente> ALTERAR_CLIENTE(@Header("Authorization") String token, @Body Cliente cliente);
}
