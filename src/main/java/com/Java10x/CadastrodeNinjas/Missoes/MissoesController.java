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
    public ResponseEntity<List<MissoesDTO>> listarMissao(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok().body(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista Missão por ID", description = "Recebe ID por parâmetro da URL e busca no banco de dados")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada no banco de dados")
    })
    public MissoesDTO listarMissaoPorID(@PathVariable Long id){
        return missoesService.listarPorID(id)
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
