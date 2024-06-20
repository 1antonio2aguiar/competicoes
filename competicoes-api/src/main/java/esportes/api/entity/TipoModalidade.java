package esportes.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    private String descricao;

    
    public void filtrar(){

    }
}
