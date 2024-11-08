package com.Java10x.CadastrodeNinjas.Missoes;

import com.Java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import com.Java10x.CadastrodeNinjas.Ninjas.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository){
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    public MissoesModel criarMissao(MissoesModel missoesModel){
        return missoesRepository.save(missoesModel);
    }

    public MissoesModel listarPorID(Long id){
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        return missoesModel.orElse(null);
    }

    public void deletarPorId(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesModel atualizarPorId(Long id, MissoesModel missoes){
        if(missoesRepository.existsById(id)){
            missoes.setId(id);
            return missoesRepository.save(missoes);
        }
        return null;
    }

}
