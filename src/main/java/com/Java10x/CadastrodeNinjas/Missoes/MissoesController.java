package com.Java10x.CadastrodeNinjas.Missoes;

import com.Java10x.CadastrodeNinjas.Ninjas.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService){
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public List<MissoesDTO> listarMissao(){
        return missoesService.listarMissoes();
    }

    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoesDTO){
        return missoesService.criarMissao(missoesDTO);
    }

    @PatchMapping("/edit/{id}")
    public MissoesDTO alterarMissao(@PathVariable Long id,@RequestBody MissoesDTO missoesDTO){
        return missoesService.atualizarPorId(id, missoesDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missoesService.deletarPorId(id);
    }

}
