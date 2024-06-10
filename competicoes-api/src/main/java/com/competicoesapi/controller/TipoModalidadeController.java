package com.competicoesapi.controller;

import com.competicoesapi.dto.tipoModalidade.DadosTipoModalidadeRcd;
import com.competicoesapi.entity.TipoModalidade;
import com.competicoesapi.repository.TipoModalidadeRepository;
import com.competicoesapi.service.exceptions.TipoModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/tipoModalidade")
public class TipoModalidadeController {
    @Autowired private TipoModalidadeService tipoModalidadeService;
    @Autowired private TipoModalidadeRepository tipoModalidadeRepository;

    // Listar tipos modalidades
    @GetMapping
    public Page<DadosTipoModalidadeRcd> findall(@PageableDefault (sort={"id"}) Pageable paginacao){
        return tipoModalidadeRepository.findAll(paginacao).map(DadosTipoModalidadeRcd::new);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DadosTipoModalidadeRcd> findById(@PathVariable Long id){
        TipoModalidade tipoModalidade = tipoModalidadeService.findById(id);
        return ResponseEntity.ok().body(new DadosTipoModalidadeRcd(tipoModalidade));
    }

    // Inserir
    @PostMapping
    @Transactional
    public ResponseEntity insert(@RequestBody @Valid DadosTipoModalidadeRcd dados){
        var tipoModalidadeInsert = tipoModalidadeService.insert(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                .buildAndExpand(tipoModalidadeInsert.getId()).toUri();
        return ResponseEntity.created(uri).body(tipoModalidadeInsert);
    }
}
