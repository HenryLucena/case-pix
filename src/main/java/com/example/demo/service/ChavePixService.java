package com.example.demo.service;

import com.example.demo.exceptionHandler.ChavePixException;
import com.example.demo.exceptionHandler.InternalServerErrorException;
import com.example.demo.exceptionHandler.ChavePixNotFoundException;
import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoPessoa;
import com.example.demo.repository.ChavePixRepository;
import com.example.demo.request.CreateChavePixRequest;
import com.example.demo.request.UpdateChavePixRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.demo.service.validaChave.*;
import static java.util.Objects.nonNull;
import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class ChavePixService {

    @Autowired
    ChavePixRepository chavePixRepository;

    @Transactional
    public ChavePix cadastrarChavePix(CreateChavePixRequest request){
        try {
            if(!isChaveValida(request.getValorChave(), request.getTipoChave())) {
                throw new ChavePixException("Formato de chave inválido");
            }

            if(nonNull(chavePixRepository.findByValorChave(request.getValorChave()))) {
                throw new ChavePixException("Já existe uma chave criada com este valor");
            }

            if (request.getTipoPessoa().equals(TipoPessoa.F)){
                ArrayList<ChavePix> chavesPix = chavePixRepository.findAllByConta(request.getNumeroConta());
                if (chavesPix.size() >= 5) throw new ChavePixException("Número máximo de chaves atingido");
            }

            if(request.getTipoPessoa().equals(TipoPessoa.J)){
                ArrayList<ChavePix> chavesPix = chavePixRepository.findAllByConta(request.getNumeroConta());
                if (chavesPix.size() >= 20) throw new ChavePixException("Número máximo de chaves atingido");
            }

            ChavePix chavePix = new ChavePix(request);
            chavePixRepository.save(chavePix);

            return chavePix;
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @Transactional
    public ChavePix atualizarChavePix(UpdateChavePixRequest request){
        try {
            Optional<ChavePix> optionalChavePix = chavePixRepository.findById(request.getId());

            if(isEmpty(optionalChavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            ChavePix chavePix = optionalChavePix.get();

            if(!chavePix.isAtiva()) throw new ChavePixException("Não foi possível atualizar. Chave Inativa!");

            if(!isChaveValida(request.getValorChave(), request.getTipoChave())) {
                throw new ChavePixException("Formato de chave inválido");
            }

            chavePix.setValorChave(request.getValorChave());
            chavePixRepository.save(chavePix);

            return chavePix;
        }catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    public ChavePix inativarChave(UUID uuid){
        try {
            Optional<ChavePix> optionalChavePix = chavePixRepository.findById(uuid);
            if(isEmpty(optionalChavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            ChavePix chavePix = optionalChavePix.get();

            if (!chavePix.isAtiva()) {throw new ChavePixException("Esta chave já está desativada");}

            chavePix.setAtiva(false);
            chavePix.setDataInativacao(LocalDateTime.now());
            chavePixRepository.save(chavePix);

            return chavePix;
        } catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    public ChavePix getById(UUID id){
        try {
            Optional<ChavePix> optionalChavePix = chavePixRepository.findById(id);
            if(isEmpty(optionalChavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            return optionalChavePix.get();
        } catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
        }
    }

    public List<ChavePix> getByTipoChave(String tipoChave){
        try {
            List<ChavePix> chavePix = chavePixRepository.findByTipoChave(tipoChave);
            if(isEmpty(chavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            return chavePix;
        } catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
        }
    }

    public List<ChavePix> getByAgenciaConta(int agencia, int conta){
        try {
            List<ChavePix> chavePix = chavePixRepository.findByAgenciaConta(agencia, conta);
            if(isEmpty(chavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            return chavePix;
        } catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
        }
    }

    public List<ChavePix> getByNomeConta(String nome, int conta){
        try {
            List<ChavePix> chavePix = chavePixRepository.findByNomeConta(nome, conta);
            if(isEmpty(chavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            return chavePix;
        } catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
        }
    }

    public List<ChavePix> getByNomeTipoChave(String nome, String tipoChave){
        try {
            List<ChavePix> chavePix = chavePixRepository.findByNomeTipoChave(nome, tipoChave);
            if(isEmpty(chavePix)) throw new ChavePixNotFoundException("Chave informada não encontrada");

            return chavePix;
        } catch (ChavePixNotFoundException e){
            throw new ChavePixNotFoundException(e.getMessage(), e);
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
