package com.Java10x.CadastrodeNinjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissoesMapper{

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoes = new MissoesModel();
        missoes.setId(missoesDTO.getId());
        missoes.setNinjas(missoesDTO.getNinjas());
        missoes.setNome(missoesDTO.getNome());
        missoes.setDificuldade(missoesDTO.getDificuldade());

        return missoes;
    }

    public MissoesDTO missoes(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setNinjas(missoesModel.getNinjas());
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());

        return missoesDTO;
    }

}
