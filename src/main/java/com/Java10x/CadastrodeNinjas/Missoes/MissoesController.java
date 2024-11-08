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
    public List<MissoesModel> listarMissao(){
        return missoesService.listarMissoes();
    }

    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel){
        return missoesService.criarMissao(missoesModel);
    }

    @PutMapping("/alterar/:id")
    public MissoesModel alterarMissao(@PathVariable Long id,@RequestBody MissoesModel missoesModel){
        return missoesService.atualizarPorId(id, missoesModel);
    }

    @DeleteMapping("/deletar/:id")
    public void deletarMissao(@PathVariable Long id){
        missoesService.deletarPorId(id);
    }
}
