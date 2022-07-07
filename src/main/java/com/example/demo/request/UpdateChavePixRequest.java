package com.example.demo.request;

import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoConta;
import com.example.demo.model.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class UpdateChavePixRequest {

    @NotNull(message = "ID da chave é obrigatório.")
    UUID id;

    @NotNull(message = "Tipo da conta e obrigatório.")
    TipoConta tipoConta;

    @NotNull(message = "Tipo da chave e obrigatorio.")
    TipoChave tipoChave;

    @NotBlank(message = "Valor da chave é obrigatorio")
    String valorChave;

    @NotNull(message = "Numero da agencia é obrigatório")
    Integer numeroAgencia;

    @NotNull(message = "Numero da conta é obrigatório.")
    Integer numeroConta;

    @NotNull(message = "Tipo da pessoa é obrigatório.")
    TipoPessoa tipoPessoa;

    @NotBlank(message = "Nome do correntista é obrigatório.")
    String nomeCorrentista;

    String sobrenomeCorrentista;
}
