package com.example.denticare.api.Api;

import android.content.Context;
import android.content.SharedPreferences;




        private static final String PREF_NAME = "TokenPrefs";
        private static final String KEY_TOKEN = "token";
        private static final String KEY_ROLE = "role";

        private SharedPreferences preferences;

        public TokenManager(Context context) {
            preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        public void saveToken(String token) {
            preferences.edit().putString(KEY_TOKEN, token).apply();
        }

        public String getToken() {
            return preferences.getString(KEY_TOKEN, null);
        }

        public void clearToken() {
            preferences.edit().remove(KEY_TOKEN).apply();
        }


        public void saveRole(String role) {
            preferences.edit().putString(KEY_ROLE, role).apply();
        }

        public String getRole() {
            return preferences.getString(KEY_ROLE, null);
        }
    }

