package com.example.denticare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.denticare.cadastro.AtualizaCad;
import com.example.denticare.cadastro.CadCliente;
import com.example.denticare.cadastro.Login;
import com.example.denticare.cell.InicialCell;
import com.example.denticare.opcoes.OpcaoCadUsuario;

public class ConfirLogin {

    public static void showCustomDialog(Context context, final ConfirLogin.CustomDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_confir_login, null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        Button btCancel = view.findViewById(R.id.btCancel);
        Button btEntrar = view.findViewById(R.id.btEntrar);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InicialCell.class);
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Login.class);
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public interface CustomDialogListener {
        void onNegativeButtonClick();
    }
}