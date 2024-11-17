package com.Java10x.CadastrodeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Lista todas as missões", description = "Rota responsável por listar todas as missões cadastradas no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso.")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissao() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok().body(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista missão por ID", description = "Rota responsável por buscar uma missão específica no banco de dados com base no ID passado na URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada no banco de dados.")
    })
    public ResponseEntity<?> listarMissaoPorID(
            @Parameter(description = "ID da missão a ser buscada.") @PathVariable Long id) {
        MissoesDTO missao = missoesService.listarPorID(id);
        if (missao != null) {
            return ResponseEntity.ok().body(missao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missão", description = "Rota responsável por criar uma nova missão no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso.")
    })
    public ResponseEntity<MissoesDTO> criarMissao(
            @Parameter(description = "Dados da missão a ser criada no banco de dados.") @RequestBody MissoesDTO missoesDTO) {
        MissoesDTO newMissao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMissao);
    }

    @PatchMapping("/edit/{id}")
    @Operation(summary = "Edita uma missão por ID", description = "Rota responsável por editar uma missão específica com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão editada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada no banco de dados.")
    })
    public ResponseEntity<?> alterarMissao(
            @Parameter(description = "ID da missão a ser editada.") @PathVariable Long id,
            @Parameter(description = "Dados atualizados da missão.") @RequestBody MissoesDTO missoesDTO) {
        if (missoesService.listarPorID(id) != null) {
            MissoesDTO missaoAtualizada = missoesService.atualizarPorId(id, missoesDTO);
            return ResponseEntity.ok().body(missaoAtualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma missão por ID", description = "Rota responsável por deletar uma missão do banco de dados com base no ID passado na URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada no banco de dados.")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "ID da missão a ser deletada.") @PathVariable Long id) {
        if (missoesService.listarPorID(id) != null) {
            missoesService.deletarPorId(id);
            return ResponseEntity.ok().body("Missão deletada!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }
}
