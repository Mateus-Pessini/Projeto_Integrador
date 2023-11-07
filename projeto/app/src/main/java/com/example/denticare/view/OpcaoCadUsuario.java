package com.example.denticare.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.denticare.R;

public class OpcaoCadUsuario {

    public static void showCustomDialog(Context context, final CustomDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_opcao_cad_usuario, null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        Button btOpcaoAtualizar = view.findViewById(R.id.btOpcaoAtualizar);
        Button btOpcaoCadastrar = view.findViewById(R.id.btOpcaoCadastrar);
        Button btOpcaoRemover = view.findViewById(R.id.btOpcaoRemover);

        btOpcaoAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AtualizaCad.class);
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });

        btOpcaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CadCliente.class);
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });
        btOpcaoRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeleteCliente.class);
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
