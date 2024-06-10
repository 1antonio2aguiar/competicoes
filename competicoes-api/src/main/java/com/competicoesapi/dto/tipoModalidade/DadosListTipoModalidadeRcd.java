package codiub.transporte.api.DTO.fabricante;

import codiub.transporte.api.entity.Fabricante;

import java.util.Optional;

public record DadosListFabricanteRcd(Long id, String nome,String nacionalidade) {
        public  DadosListFabricanteRcd(Fabricante fabricante){
                this(fabricante.getId(),
                fabricante.getNome(),
                fabricante.getNacionalidade());
        }

        public DadosListFabricanteRcd(Optional<Fabricante> fabricante) {
                this(fabricante.get().getId(),
                        fabricante.get().getNome(),
                        fabricante.get().getNacionalidade());
        }
}
