package esportes.api.dto.tipoModalidade;

import esportes.api.entity.TipoModalidade;

import java.util.Optional;

public record DadosListTipoModalidadeRcd (Long id, String nome, String descricao){

    public  DadosListTipoModalidadeRcd(TipoModalidade tipoModalidade){
        this(tipoModalidade.getId(),
                tipoModalidade.getNome(),
                tipoModalidade.getDescricao());
    }
    public DadosListTipoModalidadeRcd(Optional<TipoModalidade> tipoModalidade) {
        this(tipoModalidade.get().getId(),
                tipoModalidade.get().getNome(),
                tipoModalidade.get().getDescricao());
    }
}
