package com.example.denticare.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.denticare.R;

public class DescricaoConsulta {

    public static void showCustomDialog(Context context, final DescricaoConsulta.CustomDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_descricao_consulta, null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        Button btCancel = view.findViewById(R.id.btCancel);
        Button btSalvar = view.findViewById(R.id.btSalvar);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EscolhaDente.class);
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EscolhaDente.class);
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