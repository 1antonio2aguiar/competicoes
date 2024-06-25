package esportes.api.dto.tipoModalidade;

import jakarta.validation.constraints.NotBlank;

public record DadosInsertTipoModalidadeRcd(
        @NotBlank(message = "nome.obrigatório")
        String nome,
        String descricao
) {}
