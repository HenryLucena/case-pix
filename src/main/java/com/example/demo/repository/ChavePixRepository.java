package com.example.demo.repository;

import com.example.demo.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.valor_chave = ?1",
            nativeQuery = true)
    ChavePix findByValorChave(String valorChave);

    @Query(
            value = "SELECT * FROM chave_pix cp WHERE cp.numero_conta = ?1",
            nativeQuery = true)
    ArrayList<ChavePix> findAllByConta(int numeroConta);
}
