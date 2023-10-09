package com.example.denticare.api.Api;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFit {

    public static final String BASE_URL_API =
        "http://10.0.2.2:8080/";


    public static final String EMAIL_PESSOA = "email";
    public static final String ID_USUARIO = "id";

    private static retrofit2.Retrofit retrofit1 = new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //Login

    public static ApiUser LOGIN_CALL() {
        return retrofit1.create(ApiUser.class);
    }


    //User

    public static ApiUser REGISTER_USER(){
        return retrofit1.create(ApiUser.class);
    }

    public static ApiUser GET_USUARIO(){ return retrofit1.create(ApiUser.class);}

    public static ApiUser GET_ALL_USUARIO(){
        return retrofit1.create(ApiUser.class);
    }

    public static ApiUser ALTERAR_USUARIO() { return retrofit1.create(ApiUser.class); }

    public static ApiUser SENHA_USUARIO() { return retrofit1.create(ApiUser.class); }

    public static ApiUser DELETAR_USUARIO() { return retrofit1.create(ApiUser.class); }


}
