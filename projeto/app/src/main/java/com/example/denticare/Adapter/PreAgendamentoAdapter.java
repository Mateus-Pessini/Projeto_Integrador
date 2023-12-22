package com.example.denticare.Adapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiPreAgendamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.PreAgendamento;
import com.example.denticare.view.Agenda;
import com.example.denticare.view.CadastroPreAgendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreAgendamentoAdapter extends BaseAdapter {

    private Context context;
    private List<PreAgendamento> listaAgendamento;
    private PreAgendamento agendamentoClicando;

    String DATA = "";
    public PreAgendamentoAdapter(Context context, List<PreAgendamento> listaAgendamento) {
        this.context = context;
        this.listaAgendamento = listaAgendamento;
    }

    @Override
    public int getCount() {
        return listaAgendamento.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAgendamento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_preagendamento_grid, parent, false);
        }

        ImageView iconRemover = convertView.findViewById(R.id.iconRemover);
        ImageView iconConfirmar = convertView.findViewById(R.id.iconConfirmar);
        ImageView iconEditar = convertView.findViewById(R.id.iconEditar);
        TextView txtNomePessoa = convertView.findViewById(R.id.txtNomePessoa);
        TextView txtDataAgendamento = convertView.findViewById(R.id.txtDataAgendamento);
        //EditText edDataEdit = convertView.findViewById(R.id.edtDataEdit);
       // edDataEdit.setInputType(InputType.TYPE_NULL);
        PreAgendamento preAgendamento = listaAgendamento.get(position);

        txtNomePessoa.setText(preAgendamento.getPessoa().getNome());
        txtDataAgendamento.setText(preAgendamento.getData());

        iconRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agendamentoClicando = listaAgendamento.get(position);

                SharedPreferences sharedPreferences = context.getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");

                ApiPreAgendamento apiPreAgendamento = RetroFit.DELETE_PRE_AGENDAMENTO();

                Call<Void> call = apiPreAgendamento.DELETE_PRE_AGENDAMENTO("Bearer " + token, agendamentoClicando.getId());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            listaAgendamento.remove(getItem(position));
                            notifyDataSetChanged();
                        } else {
                            Log.e("", "Id Agendamento" + agendamentoClicando.getId());
                            //Toast.makeText(context, "Não foi possível excluir o pré-agendamento.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Lidar com a falha na chamada à API, se necessário
                    }
                });
            }


        });

        iconEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog();
                agendamentoClicando = listaAgendamento.get(position);

                SharedPreferences sharedPreferences = context.getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");



                ApiPreAgendamento apiPreAgendamento = RetroFit.EDITAR_PRE_AGENDAMENTO();
                    agendamentoClicando.setData(DATA.toString());

                Call<PreAgendamento> call = apiPreAgendamento.EDITAR_PRE_AGENDAMENTO("Bearer " + token, agendamentoClicando);
                call.enqueue(new Callback<PreAgendamento>() {
                    @Override
                    public void onResponse(Call<PreAgendamento> call, Response<PreAgendamento> response) {
                        if (response.isSuccessful()) {
                            notifyDataSetChanged();
                        } else {
                            Log.e("", "Id Agendamento" + agendamentoClicando.getId());
                            //Toast.makeText(context, "Não foi possível excluir o pré-agendamento.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PreAgendamento> call, Throwable t) {

                    }
                });
            }
        });



        return convertView;

    }

    private void showDateTimeDialog() {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));//Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        sdf.setLenient(false);
                        sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

                        DATA = (sdf.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

        new DatePickerDialog(context, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

}