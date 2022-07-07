package com.example.demo.request;

import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoConta;
import com.example.demo.model.TipoPessoa;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateChavePixRequest {

    @NotNull(message = "Tipo da chave e obrigatorio.")
    TipoChave tipoChave;

    @NotBlank(message = "Valor da chave é obrigatorio")
    String valorChave;

    @NotNull(message = "Tipo da conta e obrigatorio.")
    TipoConta tipoConta;

    @NotNull(message = "Numero da agencia é obrigatorio")
    Integer numeroAgencia;

    @NotNull(message = "Numero da conta é obrigatório.")
    Integer numeroConta;

    @NotBlank(message = "Nome do correntista é obrigatório.")
    String nomeCorrentista;
    String sobrenomeCorrentista;

    @NotNull(message = "Tipo da pessoa é obrigatório.")
    TipoPessoa tipoPessoa;

}
