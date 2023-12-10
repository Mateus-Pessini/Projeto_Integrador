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

    //PESSOA

    public static ApiPessoa REGISTER_PESSOA() {return retrofit1.create(ApiPessoa.class);}

    public static ApiPessoa UPDATE_DADOS_DENTISTA() {return retrofit1.create(ApiPessoa.class);}

    public  static  ApiPessoa GET_ALL_PESSOA(){return retrofit1.create(ApiPessoa.class);}

    public static ApiPessoa GET_PESSOA(){ return retrofit1.create(ApiPessoa.class); }

    public static ApiPessoa DELETAR_PESSOA(){ return  retrofit1.create(ApiPessoa.class); }

    //ENDERECO

    public static ApiEndereco REGISTER_ENDERECO() {return retrofit1.create(ApiEndereco.class);}

    public  static  ApiEndereco GET_ALL_ENDERECO(){return retrofit1.create(ApiEndereco.class);}

    public static ApiEndereco GET_ENDERECO(){ return retrofit1.create(ApiEndereco.class); }

    public static ApiEndereco DELETAR_ENDERECO(){ return  retrofit1.create(ApiEndereco.class); }

    //CLIENTE

    public static ApiCliente REGISTER_CLIENTE() {return retrofit1.create(ApiCliente.class);}

    public  static  ApiCliente GET_ALL_CLIENTE(){return retrofit1.create(ApiCliente.class);}

    public static ApiCliente GET_CLIENTE(){ return retrofit1.create(ApiCliente.class); }

    public static ApiCliente DELETAR_CLIENTE(){ return  retrofit1.create(ApiCliente.class); }

    //ESTADO

    public static ApiEstado GET_ALL_ESTADO(){return retrofit1.create(ApiEstado.class);}

    //CIDADE

    public static ApiCidade GET_ALL_BY_ESTADO(){return retrofit1.create(ApiCidade.class);}

    //TRATAMENTO
    public static ApiTratamento REGISTER_TRATAMENTO() {return retrofit1.create(ApiTratamento.class);}

    //PREAGENDAMENTO
    public static ApiPreAgendamento REGISTER_PRE_AGENDAMENTO() {return retrofit1.create(ApiPreAgendamento.class);}

    public static ApiPreAgendamento GET_ALL_PRE_AGENDAMENTO(){return retrofit1.create(ApiPreAgendamento.class);}

}
