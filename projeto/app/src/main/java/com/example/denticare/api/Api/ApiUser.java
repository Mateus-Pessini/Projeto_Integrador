package com.example.denticare.api.Api;

import com.example.denticare.api.models.user.AuthenticationDTO;
import com.example.denticare.api.models.user.LoginResponseDTO;
import com.example.denticare.api.models.user.RegisterDTO;
import com.example.denticare.api.models.user.UsuarioDTO;
import com.example.denticare.api.models.user.UsuarioEditDTO;
import com.example.denticare.api.models.user.UsuarioSenhaDTO;

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

public interface
ApiUser {

    @Headers("Content-Type: application/json")
    @POST("auth/register")
    Call<Void> REGISTER_USER(@Header("Authorization") String token, @Body RegisterDTO registerDTO);

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<LoginResponseDTO> LOGIN_CALL(@Body AuthenticationDTO authenticationDTO);

    @GET("usuario")
    Call<List<UsuarioDTO>> GET_ALL_USUARIO(@Header("Authorization") String authorization);

    @GET("usuario/{id}")
    Call<UsuarioDTO> GET_USUARIO(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("usuario/alterar-senha")
    Call<UsuarioDTO> SENHA_USUARIO(@Header("Authorization") String token, @Body UsuarioSenhaDTO usuarioSenhaDTO );

    @Headers("Content-Type: application/json")
    @PUT("usuario")
    Call<UsuarioDTO> ALTERAR_USUARIO(@Header("Authorization") String token, @Body UsuarioEditDTO usuarioEditDTO);

    @DELETE("usuario/{id}")
    Call<UsuarioDTO> DELETE_USUARIO(@Header("Authorization") String authorization, @Path("id") Long id);




}
