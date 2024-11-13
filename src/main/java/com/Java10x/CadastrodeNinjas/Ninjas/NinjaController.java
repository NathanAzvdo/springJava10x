package com.Java10x.CadastrodeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    @Operation(summary = "Cria novo ninja", description = "Rota responsável por ninjainserir no banco de dados")
    @ApiResponses(value={
        @ApiResponse(responseCode="201")
    })
    public ResponseEntity<NinjaDTO> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO Newninja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body(Newninja);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok().body(ninjas);

    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista ninja por ID", description = "Recebe ID por parâmetro da URL e busca no banco de dados")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado no banco de dados")
    })
    public ResponseEntity<?> listarNinjasPorID(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjasPorID(id);
        if(ninja!= null){
            return ResponseEntity.ok().body(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/alterarID/{id}")
    @Operation(summary = "Altera ninja por ID", description = "Rota responsável por alterar ninja no banco de dados com base no ID da URL")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description = "Ninja atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao atualizar o ninja ou ninja não encontrado")
    })
    public ResponseEntity<?> alterarNinjasPorID(
            @Parameter(description = "Usuário manda ID pelo Path")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda dados atualizados no corpo da requisição")
            @RequestBody NinjaDTO ninja){

        if(ninjaService.listarNinjasPorID(id)!=null){
            NinjaDTO newNinja = ninjaService.atualizarPorId(id, ninja);
            return ResponseEntity.ok().body(newNinja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if(ninjaService.listarNinjasPorID(id)!=null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok().body("Ninja deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
    }
}
