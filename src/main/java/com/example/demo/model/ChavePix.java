package com.example.demo.model;

import com.example.demo.request.CreateChavePixRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chave_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    TipoChave tipoChave;

    @Column(unique = true, length = 77, nullable = false)
    String valorChave;

    @Column(nullable = false)
    TipoConta tipoConta;

    @Column(nullable = false)
    @Max(9999)
    int numeroAgencia;

    @Column(nullable = false, length = 8)
    @Max(99999999)
    int numeroConta;

    @Column(length = 30, nullable = false)
    String nomeCorrentista;

    @Column(length = 45)
    String sobrenomeCorrentista;

    @Column(nullable = false)
    TipoPessoa tipoPessoa;

    @CreationTimestamp
    private LocalDateTime dataCriação;

    private LocalDateTime dataInativacao;

    private boolean isAtiva;

    public ChavePix(){}

    public ChavePix(CreateChavePixRequest request){
        this.tipoChave = request.getTipoChave();
        this.valorChave = request.getValorChave();
        this.tipoConta = request.getTipoConta();
        this.numeroAgencia = request.getNumeroAgencia();
        this.numeroConta = request.getNumeroConta();
        this.nomeCorrentista = request.getNomeCorrentista();
        this.sobrenomeCorrentista = request.getSobrenomeCorrentista();
        this.tipoPessoa = request.getTipoPessoa();
        this.isAtiva = true;
    }
}
