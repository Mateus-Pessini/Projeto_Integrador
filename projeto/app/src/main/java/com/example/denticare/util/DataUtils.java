package com.example.denticare.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

public class DataUtils {

    public static String getDataFromTokenToShow(Context c, String type) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (!token.isEmpty())  {
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] tokenSplited = token.split("\\.");
            String header = new String(decoder.decode(tokenSplited[0]));
            String payload = new String(decoder.decode(tokenSplited[1]));
            if (type.equals("name")) {
                String name;
                try {
                    name = new JSONObject(payload).getString("Name");
                } catch (JSONException e) {
                    name = "";
                }
                return name;
            }

            if (type.equals("role")) {
                String name;
                try {
                    name = new JSONObject(payload).getString("Role");
                } catch (JSONException e) {
                    name = "";
                }
                return name;
            }

            if (type.equals("cpf")) {
                String name;
                try {
                    name = new JSONObject(payload).getString("Cpf");
                } catch (JSONException e) {
                    name = "";
                }
                return name;
            }

            if (type.equals("id")) {
                String name;
                try {
                    name = new JSONObject(payload).getString("Id");
                } catch (JSONException e) {
                    name = "";
                }
                return name;
            }

        }
        return null;
    }

    public static String getToken(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if (!token.isEmpty())  {
            return token;
        }
        return null;
    }

}
