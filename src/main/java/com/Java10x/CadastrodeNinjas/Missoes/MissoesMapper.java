package com.Java10x.CadastrodeNinjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissoesMapper{

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNinjas(missoesDTO.getNinjas());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setNinjas(missoesModel.getNinjas());
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());

        return missoesDTO;
    }

}
