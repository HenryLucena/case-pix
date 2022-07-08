package com.example.demo.response;

import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoConta;
import com.example.demo.model.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class AtualizaChavePixResponse {

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

    public AtualizaChavePixResponse(ChavePix chavePix) {
        this.id = chavePix.getId();
        this.tipoChave = chavePix.getTipoChave();
        this.valorChave = chavePix.getValorChave();
        this.tipoConta = chavePix.getTipoConta();
        this.numeroAgencia = chavePix.getNumeroAgencia();
        this.numeroConta = chavePix.getNumeroConta();
        this.nomeCorrentista = chavePix.getNomeCorrentista();
        this.sobrenomeCorrentista = chavePix.getSobrenomeCorrentista();
        this.tipoPessoa = chavePix.getTipoPessoa();
        this.dataHoraInclusao = this.dateFormat(chavePix.getDataCriacao());
    }

    private String dateFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
        return formatter.format(dateTime);
    }
}
