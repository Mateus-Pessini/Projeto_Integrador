package com.example.denticare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class tela_consulta extends AppCompatActivity {

    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        tableLayout = findViewById(R.id.tableLayout);
        
        String[][] dados = {
                {"Status 1", "08:00", "Nome 1", "Tipo 1", "Data 1", "Valor 1"},
                {"Status 2", "08:30", "Nome 2", "Tipo 2", "Data 2", "Valor 2"},
                {"Status 3", "09:00", "Nome 3", "Tipo 3", "Data 3", "Valor 3"}
        };

        for (int i = 0; i < dados.length; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            row.setLayoutParams(layoutParams);

            for (int j = 0; j < dados[i].length; j++) {
                TextView cell = new TextView(this);
                cell.setText(dados[i][j]);
                cell.setPadding(10, 10, 10, 10);
                row.addView(cell);
            }

            tableLayout.addView(row);
        }

    }
}