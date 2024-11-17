package com.Java10x.CadastrodeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Cria novo ninja", description = "Rota responsável por inserir um novo ninja no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso.")
    })
    public ResponseEntity<NinjaDTO> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO Newninja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body(Newninja);
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Retorna uma lista com todos os ninjas cadastrados no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso.")
    })
    public ResponseEntity<List<?>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok().body(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Busca ninja por ID", description = "Recebe um ID como parâmetro da URL e busca o ninja correspondente no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado no banco de dados.")
    })
    public ResponseEntity<?> listarNinjasPorID(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorID(id);
        if (ninja != null) {
            return ResponseEntity.ok().body(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado.");
    }

    @PutMapping("/alterarID/{id}")
    @Operation(summary = "Atualiza ninja por ID", description = "Rota responsável por atualizar as informações de um ninja com base no ID passado na URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado no banco de dados.")
    })
    public ResponseEntity<?> alterarNinjasPorID(
            @Parameter(description = "ID do ninja passado como parâmetro na URL.") @PathVariable Long id,
            @Parameter(description = "Dados atualizados do ninja enviados no corpo da requisição.") @RequestBody NinjaDTO ninja) {

        if (ninjaService.listarNinjasPorID(id) != null) {
            NinjaDTO newNinja = ninjaService.atualizarPorId(id, ninja);
            return ResponseEntity.ok().body(newNinja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado.");
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta ninja por ID", description = "Rota responsável por deletar um ninja do banco de dados com base no ID passado na URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado no banco de dados.")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "ID do ninja passado como parâmetro na URL.") @PathVariable Long id) {
        if (ninjaService.listarNinjasPorID(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok().body("Ninja deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
    }
}
