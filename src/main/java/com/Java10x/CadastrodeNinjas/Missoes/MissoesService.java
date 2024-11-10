package com.Java10x.CadastrodeNinjas.Missoes;

import com.Java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import com.Java10x.CadastrodeNinjas.Ninjas.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper){
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes =  missoesRepository.findAll();
        return missoes.stream().map(missoesMapper::map).collect(Collectors.toList());
    }

    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel = missoesRepository.save(missoesModel);
        return missoesMapper.map(missoesModel);
    }

    public MissoesDTO listarPorID(Long id){
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        return missoesModel.map(missoesMapper::map).orElse(null);
    }

    public void deletarPorId(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesDTO atualizarPorId(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missoesExists = missoesRepository.findById(id);
        if(missoesExists.isPresent()){
            MissoesModel newMissao = missoesMapper.map(missoesDTO);
            newMissao.setId(id);
            MissoesModel missaoSaved = missoesRepository.save(newMissao);
            return missoesMapper.map(missaoSaved);
        }
        return null;
    }

}
