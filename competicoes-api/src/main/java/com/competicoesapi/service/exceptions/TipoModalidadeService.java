package com.competicoesapi.service.exceptions;

import com.competicoesapi.dto.tipoModalidade.DadosTipoModalidadeRcd;
import com.competicoesapi.entity.TipoModalidade;
import com.competicoesapi.repository.TipoModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TipoModalidadeService {
    @Autowired
    private TipoModalidadeRepository tipoModalidadeRepository;

    // tipo modalidade por id
    public TipoModalidade findById(Long id){
        Optional<TipoModalidade> obj = tipoModalidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Tipo modalidade não encontrado! Id: " + id + " Tipo: " + TipoModalidade.class.getName()));
    }

    //Insert
    public TipoModalidade insert(DadosTipoModalidadeRcd dados){
        return tipoModalidadeRepository.save(new TipoModalidade(dados));
    }
}
