package com.Java10x.CadastrodeNinjas.Ninjas;
import com.Java10x.CadastrodeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDTO {

    private Long NinjaID;
    private String nome;
    private int idade;
    private String imageUrl;
    private String email;
    private MissoesModel missoes;
    private String rank;

}
