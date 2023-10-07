package com.example.denticare.api.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiError {
    @SerializedName("error")
    private List<String> errorMessages;

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}