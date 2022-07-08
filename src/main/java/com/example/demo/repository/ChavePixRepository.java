package com.example.demo.repository;

import com.example.demo.model.ChavePix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.UUID;

public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.valor_chave = ?1",
            nativeQuery = true)
    ChavePix findByValorChave(String valorChave);

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.tipo_chave = ?1",
            nativeQuery = true)
    ChavePix findByTipoChave(String tipoChave);

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.numero_conta = ?1",
            nativeQuery = true)
    ArrayList<ChavePix> findAllByConta(int numeroConta);

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.numero_agencia = ?1 AND cp.numero_conta = ?2",
            nativeQuery = true)
    ChavePix findByAgenciaConta(int agencia, int conta);

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.nome_correntista = ?1 AND cp.numero_conta = ?2",
            nativeQuery = true)
    ChavePix findByNomeConta(String nome, int conta);

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.nome_correntista = ?1 AND cp.tipo_chave = ?2",
            nativeQuery = true)
    ChavePix findByNomeTipoChave(String nome, String tipoChave);

}
