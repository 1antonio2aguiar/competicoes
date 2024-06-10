package codiub.transporte.api.DTO.fabricante;

import codiub.transporte.api.entity.Fabricante;
import jakarta.validation.constraints.NotBlank;

public record DadosUpdateFabricanteRcd(
        @NotBlank(message = "nome.obrigatorio")
        String nome,
        @NotBlank(message = "A nacionalidade da montadora deve ser informada!")
        String nacionalidade) {

        public DadosUpdateFabricanteRcd(Fabricante fabricante) {
                this(fabricante.getNome(),
                     fabricante.getNacionalidade());
        }
        public String getNome(){
                return nome.toUpperCase();
        }
        public String getNacionalidade(){
                return nacionalidade.toUpperCase();
        }
}
