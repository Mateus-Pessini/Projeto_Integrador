package com.example.denticare.opcoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.denticare.R;


public class OpcaoCadUsuario {

    public interface CustomDialogListener{
        void onPositiveButtonClick();
        void onNegativeButtonClick();
    }

    public static void showCustomDialog(Context context, OpcaoCadUsuario.CustomDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_opcao_cad_usuario, null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();


        Button buttonYes = view.findViewById(R.id.btOpcaoCadastrar);
        Button buttonNo = view.findViewById(R.id.btOpcaoAtualizar);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.dismiss();
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNegativeButtonClick();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
}

}