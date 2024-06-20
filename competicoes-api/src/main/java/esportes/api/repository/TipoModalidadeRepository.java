package esportes.api.repository;

import esportes.api.entity.TipoModalidade;
import esportes.api.repository.tiposModalidades.TiposModalidadesRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoModalidadeRepository extends JpaRepository<TipoModalidade, Long>/*, TiposModalidadesRepositoryQuery*/ {
}
