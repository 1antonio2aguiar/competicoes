package esportes.api.repository;

import esportes.api.entity.TipoModalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoModalidadeRepository extends JpaRepository<TipoModalidade, Long>{
    Optional<TipoModalidade> findByDescricao(String descricao);
}
