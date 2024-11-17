package com.Java10x.CadastrodeNinjas.Missoes;

import com.Java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Entidade que representa uma missão no sistema, com dados como nome, dificuldade e a lista de ninjas associados.")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único da missão, gerado automaticamente pelo banco de dados.", example = "1")
    private Long id;

    @Schema(description = "Nome da missão.", example = "Resgatar o pergaminho sagrado")
    private String nome;

    @Schema(description = "Nível de dificuldade da missão.", example = "Alta")
    private String dificuldade;

    @OneToMany(mappedBy = "missoes")
    @JsonIgnore
    @Schema(description = "Lista de ninjas associados à missão. Essa relação é ignorada na serialização JSON.")
    private List<NinjaModel> ninjas;
}