package com.Java10x.CadastrodeNinjas.Ninjas;

import com.Java10x.CadastrodeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="tb_cadastro")
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NinjaID;
    private String Nome;
    private String email;
    private int idade;
    private List<MissoesModel> missoes;

    public NinjaModel(){

    }

    public NinjaModel(int idade, String email, String nome) {
        this.idade = idade;
        this.email = email;
        Nome = nome;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
