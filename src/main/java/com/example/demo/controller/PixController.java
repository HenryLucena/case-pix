package com.example.demo.controller;

import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.request.CreateChavePixRequest;
import com.example.demo.request.UpdateChavePixRequest;
import com.example.demo.response.AtualizaChavePixResponse;
import com.example.demo.response.ConsultaChavePixResponse;
import com.example.demo.response.InativacaoChavePixResponse;
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
import java.util.List;
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
        return new ResponseEntity<>(new AtualizaChavePixResponse(chavePixService.atualizarChavePix(request)), HttpStatus.OK);
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativarChave(@PathVariable UUID id){
        return new ResponseEntity<>(new InativacaoChavePixResponse(chavePixService.inativarChave(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaChavePixResponse> getById(@PathVariable UUID id){
        return new ResponseEntity<>(new ConsultaChavePixResponse(chavePixService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/getByTipoChave")
    public ResponseEntity<?> getByTipoChave(@RequestParam String tipoChave){
        return new ResponseEntity<>(ConsultaChavePixResponse.returnListaChaves(chavePixService.getByTipoChave(tipoChave)), HttpStatus.OK);
    }

    @GetMapping("/getByAgenciaConta")
    public ResponseEntity<?> getByAgenciaConta(@RequestParam int agencia, @RequestParam int conta){
        return new ResponseEntity<>(ConsultaChavePixResponse.returnListaChaves(chavePixService.getByAgenciaConta(agencia, conta)), HttpStatus.OK);
    }

    @GetMapping("/getByNomeConta")
    public ResponseEntity<?> getByNomeConta(@RequestParam String nome, @RequestParam int conta){
        return new ResponseEntity<>(ConsultaChavePixResponse.returnListaChaves(chavePixService.getByNomeConta(nome, conta)), HttpStatus.OK);
    }

    @GetMapping("/getByNomeTipoChave")
    public ResponseEntity<?> getByNomeTipoChave(@RequestParam String nome, @RequestParam String tipoChave){
        return new ResponseEntity<>(ConsultaChavePixResponse.returnListaChaves(chavePixService.getByNomeTipoChave(nome, tipoChave)), HttpStatus.OK);
    }
}
