package com.example.demo.controller;

import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.request.CreateChavePixRequest;
import com.example.demo.request.UpdateChavePixRequest;
import com.example.demo.service.ChavePixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static java.lang.String.valueOf;


@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    ChavePixService chavePixService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarChavePix(@Valid @RequestBody CreateChavePixRequest request) {
        ChavePix chavePix = chavePixService.cadastrarChavePix(request);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarChavePix(@Valid @RequestBody UpdateChavePixRequest request){
        ChavePix chavePix = chavePixService.atualizarChavePix(request);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativarChave(@PathVariable UUID id){
        ChavePix chavePix = chavePixService.inativarChave(id);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        ChavePix chavePix = chavePixService.getById(id);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @GetMapping("/getByTipoChave")
    public ResponseEntity<?> getByTipoChave(@RequestParam String tipoChave){
        ChavePix chavePix = chavePixService.getByTipoChave(tipoChave);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @GetMapping("/getByAgenciaConta")
    public ResponseEntity<?> getByAgenciaConta(@RequestParam int agencia, @RequestParam int conta){
        ChavePix chavePix = chavePixService.getByAgenciaConta(agencia, conta);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @GetMapping("/getByNomeConta")
    public ResponseEntity<?> getByNomeConta(@RequestParam String nome, @RequestParam int conta){
        ChavePix chavePix = chavePixService.getByNomeConta(nome, conta);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }

    @GetMapping("/getByNomeTipoChave")
    public ResponseEntity<?> getByNomeTipoChave(@RequestParam String nome, @RequestParam String tipoChave){
        ChavePix chavePix = chavePixService.getByNomeTipoChave(nome, tipoChave);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }
}
