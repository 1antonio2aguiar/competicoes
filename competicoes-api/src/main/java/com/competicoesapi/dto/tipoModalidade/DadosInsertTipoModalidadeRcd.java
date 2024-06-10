package codiub.transporte.api.DTO.fabricante;

import jakarta.validation.constraints.NotBlank;

public record DadosInsertFabricanteRcd(
        @NotBlank(message = "nome.obrigatorio")
        String nome,
        @NotBlank(message = "A nacionalidade da montadora deve ser informada!")
        String nacionalidade) {
}
