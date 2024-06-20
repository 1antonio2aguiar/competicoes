package esportes.api.repository.tiposModalidades;

import esportes.api.entity.TipoModalidade;
import esportes.api.filter.TipoModalidadeFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface TiposModalidadesRepositoryQuery {
	public Page<TipoModalidade> filtrar(TipoModalidadeFilter tipoModalidadeFilter, Pageable pageable);
}
