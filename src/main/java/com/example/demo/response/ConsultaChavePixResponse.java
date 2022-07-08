package com.example.demo.response;

import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoConta;
import com.example.demo.model.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ConsultaChavePixResponse {

    private UUID id;
    private TipoChave tipoChave;
    private String valorChave;
    private TipoConta tipoConta;
    private int numeroAgencia;
    private int numeroConta;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private TipoPessoa tipoPessoa;
    private String dataHoraInclusao;

    public ConsultaChavePixResponse(){}

    public ConsultaChavePixResponse(ChavePix chavePix) {
        this.id = chavePix.getId();
        this.tipoChave = chavePix.getTipoChave();
        this.valorChave = chavePix.getValorChave();
        this.tipoConta = chavePix.getTipoConta();
        this.numeroAgencia = chavePix.getNumeroAgencia();
        this.numeroConta = chavePix.getNumeroConta();
        this.tipoPessoa = chavePix.getTipoPessoa();
        this.nomeCorrentista = chavePix.getNomeCorrentista();
        this.sobrenomeCorrentista = chavePix.getSobrenomeCorrentista();
        this.dataHoraInclusao = this.dateFormat(chavePix.getDataCriacao());
    }

    public static List<ConsultaChavePixResponse> returnListaChaves(List<ChavePix> chavePixList){

        List<ConsultaChavePixResponse> listaToReturn = new ArrayList<>();

        for(ChavePix chavePix : chavePixList){
            listaToReturn.add(new ConsultaChavePixResponse(chavePix));
        }

        return listaToReturn;
    }

    private String dateFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
        return formatter.format(dateTime);
    }
}
