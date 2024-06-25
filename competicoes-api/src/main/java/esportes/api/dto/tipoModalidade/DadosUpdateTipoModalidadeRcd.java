package esportes.api.dto.tipoModalidade;

import esportes.api.entity.TipoModalidade;

import jakarta.validation.constraints.NotBlank;

public record DadosUpdateTipoModalidadeRcd(
    @NotBlank(message = "nome.obrigatorio")
    String nome,
    String descricao ) {

    public DadosUpdateTipoModalidadeRcd(TipoModalidade tipoModalidade) {
        this(tipoModalidade.getNome(),
                tipoModalidade.getDescricao());
    }
    public String getNome(){
        return nome.toUpperCase();
    }
}
