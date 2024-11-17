package com.Java10x.CadastrodeNinjas.Ninjas;
import com.Java10x.CadastrodeNinjas.Missoes.MissoesModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Representação de um ninja no sistema.")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Identificador único do ninja.", example = "1")
    private Long NinjaID;

    @Schema(description = "Nome do ninja.", example = "Naruto Uzumaki")
    private String nome;

    @Schema(description = "Idade do ninja.", example = "16")
    private int idade;

    @Column(name = "img_url")
    @Schema(description = "URL da imagem do ninja.", example = "https://example.com/naruto.png")
    private String imageUrl;

    @Column(unique = true)
    @Schema(description = "E-mail único do ninja.", example = "naruto@konoha.com")
    private String email;

    @ManyToOne
    @JoinColumn(name = "missoes_id")
    @Schema(description = "Missões associadas ao ninja.")
    private MissoesModel missoes;

    @Column(name = "rank")
    @Schema(description = "Rank do ninja (Ex: Genin, Chunin, Jonin).", example = "Hokage")
    private String rank;
}