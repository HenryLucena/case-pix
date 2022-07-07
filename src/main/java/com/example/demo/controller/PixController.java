package com.example.demo.controller;

import com.example.demo.model.ChavePix;
import com.example.demo.request.CreateChavePixRequest;
import com.example.demo.service.ChavePixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    ChavePixService chavePixService;

    @PostMapping("/create")
    public ResponseEntity<?> criarChavePix(@Valid @RequestBody CreateChavePixRequest request) {
        ChavePix chavePix = chavePixService.cadastrarChavePix(request);
        return new ResponseEntity<>(chavePix, HttpStatus.OK);
    }
}
