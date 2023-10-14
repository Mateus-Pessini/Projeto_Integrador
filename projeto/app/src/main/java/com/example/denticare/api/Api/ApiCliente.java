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
    @POST("Cliente")
    Call<Cliente> REGISTER_CLIENTE(@Header("Authorization") String token, @Body Cliente cliente);

    @GET("Cliente")
    Call<List<Cliente>> GET_ALL_CLIENTE(@Header("Authorization") String authorization);

    @GET("Cliente/{id}")
    Call<Cliente> GET_CLIENTE(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("Cliente/{id}")
    Call<Cliente> DELETAR_CLIENTE(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("Cliente")
    Call<Cliente> ALTERAR_CLIENTE(@Header("Authorization") String token, @Body Cliente cliente);
}
