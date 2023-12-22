package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Consulta.ConsultaList;
import br.unipar.dentiCare.models.Pessoa.*;
import br.unipar.dentiCare.repositories.CidadeRepository;
import br.unipar.dentiCare.repositories.PreAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.unipar.dentiCare.repositories.PessoaRepository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PreAgendamentoService {

    @Autowired
    PreAgendamentoRepository preAgendamentoRepository;


    public PreAgendamento insert(PreAgendamentoDTO preAgendamentoDTO) throws Exception {
        PreAgendamento pre = new PreAgendamento();

        pre.setData(preAgendamentoDTO.getData());
        pre.setPessoa(preAgendamentoDTO.getPessoa());
        filtro(pre);
        pre = preAgendamentoRepository.saveAndFlush(pre);

        return pre;
    }

    public PreAgendamento edit(PreAgendamentoDTO preAgendamento) throws Exception {
        PreAgendamento p = findById(preAgendamento.getId());
        p.setData(preAgendamento.getData());
        return preAgendamentoRepository.saveAndFlush(p);
    }

    public void remove(Long id) throws Exception {
        PreAgendamento preAgendamento = findById(id);
        preAgendamentoRepository.delete(preAgendamento);
    }

    public PreAgendamento findById(Long id) throws Exception {
        Optional<PreAgendamento> retorno = preAgendamentoRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("PreAgendamento com id " + id + " não identificado");
    }

    public List<ConsultaList> findAllWithPeopleName() {
        List<ConsultaList> consultaListRetorno = new ArrayList<>();
        List<PreAgendamento> preAgendamentos = preAgendamentoRepository.findAll();

        for (PreAgendamento preAgendamento : preAgendamentos) {
            ConsultaList consultaList = new ConsultaList();

            // Converta Date para LocalDateTime
            LocalDateTime dataHora = preAgendamento.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime novaDataHora = dataHora.plusHours(3);

            // Defina o formato desejado para a data
            DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            // Formate a data
            String dataFormatada = novaDataHora.format(formatoDesejado);

            consultaList.setData(dataFormatada);
            consultaList.setId(preAgendamento.getId());

            consultaList.setPessoa(preAgendamento.getPessoa());

            consultaListRetorno.add(consultaList);
        }

        return consultaListRetorno;
    }

    public void filtro(PreAgendamento preAgendamento) throws Exception {
        Feriados feriados = new Feriados();
        ArrayList<PreAgendamento> agendamentos = new ArrayList<>(preAgendamentoRepository.findAll());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(preAgendamento.getData());
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar today = Calendar.getInstance();
        today.setTimeZone(TimeZone.getTimeZone("UTC"));
        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.S");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < agendamentos.size(); i++) {
            if (agendamentos.get(i).getData().toString().equals(df.format(calendar.getTime()))) {
                throw new Exception("Horário ocupado, favor selecionar outro");
            }
        }
        if (dayOfWeek == 7 || dayOfWeek == 1) {
            // Fim de semana, retorne um erro ou mensagem apropriada.
            throw new Exception("Não atendemos nos finais de semana, favor escolher outra data");
        } else if (feriados.isFeriado(calendar.getTime())) {
            throw new Exception("Não atendemos nos feriados, favor escolher outra data");
        } else if (calendar.getTime().before(today.getTime())) {
            // Data passada, retorne um erro ou mensagem apropriada.
            throw new Exception("Não é possível agendar em datas passadas, favor escolher outra data");
        } else if (hourOfDay < 8 || (hourOfDay >= 12 && hourOfDay < 13) || hourOfDay >= 18) {
            // Horário restrito, retorne um erro ou mensagem apropriada.
            throw new Exception("Nosso expediente é das 8:00 ao 12:00 e das 13:00 as 18:00");
        }

    }

}

