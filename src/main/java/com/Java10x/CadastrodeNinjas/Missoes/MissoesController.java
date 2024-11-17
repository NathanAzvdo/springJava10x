package com.Java10x.CadastrodeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

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
    public ResponseEntity<?> listarMissaoPorID(@PathVariable Long id){
        MissoesDTO missao = missoesService.listarPorID(id);
        if(missao!=null){
            return ResponseEntity.ok().body(missao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @PostMapping("/criar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missoesDTO){
        MissoesDTO newMissao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMissao);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id,@RequestBody MissoesDTO missoesDTO){
        if(missoesService.listarPorID(id)!=null){
            MissoesDTO missaoAtualizada = missoesService.atualizarPorId(id, missoesDTO);
            return ResponseEntity.ok().body(missaoAtualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if(missoesService.listarPorID(id)!=null){
            missoesService.deletarPorId(id);
            ResponseEntity.ok().body("Missão deletada!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

}
