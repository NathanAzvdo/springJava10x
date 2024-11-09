package com.Java10x.CadastrodeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.criarNinja(ninja);
    }

    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjasPorID(@PathVariable Long id){
        return ninjaService.listarNinjasPorID(id);
    }

    @PutMapping("/alterarID/{id}")
    public NinjaDTO alterarNinjasPorID(@PathVariable Long id, @RequestBody NinjaDTO ninja){
        return ninjaService.atualizarPorId(id, ninja);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
    }
}
