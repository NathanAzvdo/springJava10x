package com.Java10x.CadastrodeNinjas.Missoes;

import jakarta.persistence.*;

@Entity
@Table(name="tb_missoes")
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String dificuldade;

}
