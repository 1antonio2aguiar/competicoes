package com.competicoesapi.entity;

import com.competicoesapi.dto.tipoModalidade.DadosTipoModalidadeRcd;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "TipoModalidade")
@Table(name = "tipos_modalidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoModalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    public TipoModalidade(DadosTipoModalidadeRcd dados) {
        this.nome = dados.nome().toUpperCase();
        this.descricao = dados.descricao();
    }
}
