package com.Java10x.CadastrodeNinjas.Ninjas;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas= ninjaRepository.findAll();
        return ninjas.stream().map(ninjaMapper::map).collect(Collectors.toList() );
    }

    public NinjaDTO listarNinjasPorID(Long id){
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        return ninjaModel.map(ninjaMapper::map).orElse(null) ;
    }

    public NinjaDTO criarNinja(NinjaDTO ninja){
        NinjaModel ninjaModel = ninjaMapper.map(ninja);
        ninjaModel = ninjaRepository.save(ninjaModel);
        return ninjaMapper.map(ninjaModel);
    }

    public void deletarNinjaPorId(Long id){
      ninjaRepository.deleteById(id);
    }

    public NinjaDTO atualizarPorId(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExists = ninjaRepository.findById(id);
        if(ninjaExists.isPresent()){
            NinjaModel newNinja = ninjaMapper.map(ninjaDTO);
            newNinja.setNinjaID(id);
            NinjaModel ninjaSaved = ninjaRepository.save(newNinja);
            return ninjaMapper.map(ninjaSaved);

        }
        return null;
    }


}
