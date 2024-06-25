package esportes.api.controller;

import esportes.api.dto.tipoModalidade.DadosListTipoModalidadeRcd;
import esportes.api.repository.TipoModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoModalidade")
public class TipoModalidadeController {

    @Autowired private TipoModalidadeRepository tipoModalidadeRepository;

    // Listar modalidades
    @GetMapping
    public Page<DadosListTipoModalidadeRcd> findall(@PageableDefault(sort={"descricao"}) Pageable paginacao){
        return tipoModalidadeRepository.findAll(paginacao).map(DadosListTipoModalidadeRcd::new);
    }

}
