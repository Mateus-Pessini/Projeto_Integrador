package com.example.denticare.api.models.user;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class ApiRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        String json = "{\"login\": \"" + ((AuthenticationDTO) value).getLogin() + "\","
                + "\"senha\": \"" + ((AuthenticationDTO) value).getSenha() + "\"}";
        return RequestBody.create(MEDIA_TYPE_JSON, json);
    }
}
