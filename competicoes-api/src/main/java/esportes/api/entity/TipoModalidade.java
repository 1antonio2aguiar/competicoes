package esportes.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;

import esportes.api.dto.tipoModalidade.DadosInsertTipoModalidadeRcd;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "TipoModalidade")
@Table(name = "tipos_modalidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoModalidade {
    private static final long serialVersionUID = 1L;

    @NotNull
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40, min = 3)
    private String nome;
    private String descricao;

    public TipoModalidade(DadosInsertTipoModalidadeRcd dados) {
        this.nome = dados.nome().toUpperCase();
        this.descricao = dados.descricao();
    }

    public void filtrar(){

    }
}
