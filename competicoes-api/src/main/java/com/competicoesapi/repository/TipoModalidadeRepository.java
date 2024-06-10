package com.competicoesapi.repository;

import com.competicoesapi.entity.TipoModalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TipoModalidadeRepository extends JpaRepository<TipoModalidade, Long> {
    //Optional<TipoModalidade> findByNome(String nome);
}
