package com.example.demo.service;

import com.example.demo.exceptionHandler.ChavePixExistenteException;
import com.example.demo.exceptionHandler.ChavePixInvalidaException;
import com.example.demo.exceptionHandler.InternalServerErrorException;
import com.example.demo.exceptionHandler.MaximoChavesCadastradasException;
import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoPessoa;
import com.example.demo.repository.ChavePixRepository;
import com.example.demo.request.CreateChavePixRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.service.validaChave.*;
import static java.util.Objects.nonNull;


@Service
public class ChavePixService {

    @Autowired
    ChavePixRepository chavePixRepository;

    @Transactional
    public ChavePix cadastrarChavePix(CreateChavePixRequest request){
        try {
            if(!isChaveValida(request.getValorChave(), request.getTipoChave())) {
                throw new ChavePixInvalidaException("Formato de chave inválido");
            }

            if(nonNull(chavePixRepository.findByValorChave(request.getValorChave()))) {
                throw new ChavePixExistenteException("Já existe uma chave criada com este valor");
            }

            if (request.getTipoPessoa().equals(TipoPessoa.F)){
                ArrayList<ChavePix> chavesPix = chavePixRepository.findAllByConta(request.getNumeroConta());
                if (chavesPix.size() > 5) throw new MaximoChavesCadastradasException("Número máximo de chaves atingido");
            }

            ChavePix chavePix = new ChavePix(request);
            chavePixRepository.save(chavePix);

            return chavePix;
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    private boolean isChaveValida(String valorChave, TipoChave tipoChave){
        if (tipoChave.equals(TipoChave.CPF)){ return isValidCPF(valorChave); }

        if(tipoChave.equals(TipoChave.CNPJ)) {return isValidCNPJ(valorChave);}

        if(tipoChave.equals(TipoChave.EMAIL)) {return isValidEmail(valorChave);}

        if(tipoChave.equals(TipoChave.CELULAR)) {return isValidCelular(valorChave); }

        if(tipoChave.equals(TipoChave.ALEATORIO)){ return valorChave.length() <= 36;}

        return false;
    }
}
