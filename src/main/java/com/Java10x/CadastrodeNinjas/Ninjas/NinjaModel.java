package com.Java10x.CadastrodeNinjas.Ninjas;

import com.Java10x.CadastrodeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name="tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NinjaID;
    private String Nome;
    private int idade;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name="missoes_id")
    private MissoesModel missoes;

}
