package com.example.demo.request;

import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoConta;
import com.example.demo.model.TipoPessoa;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
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

    @NotNull(message = "Numero da agencia é obrigatório")
    @Digits(fraction = 0, integer = 4, message = "Numero da agencuia deve ter até 4 digitos")
    Integer numeroAgencia;

    @NotNull(message = "Numero da conta é obrigatório.")
    @Digits(fraction = 0, integer = 8, message = "Numero da conta deve ter até 8 digitos")
    Integer numeroConta;

    @NotBlank(message = "Nome do correntista é obrigatório.")
    @Length(max = 30, message = "Nome do correntista deve ter no máximo 30 caracteres")
    String nomeCorrentista;

    @Length(max = 45, message = "Sobrenome do correntista deve ter no máximo 45 caracteres")
    String sobrenomeCorrentista;

    @NotNull(message = "Tipo da pessoa é obrigatório.")
    TipoPessoa tipoPessoa;

}
