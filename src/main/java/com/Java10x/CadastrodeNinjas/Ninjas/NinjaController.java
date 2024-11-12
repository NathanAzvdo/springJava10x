package com.Java10x.CadastrodeNinjas.Ninjas;

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
    public ResponseEntity<?> listarNinjasPorID(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjasPorID(id);
        if(ninja!= null){
            return ResponseEntity.ok().body(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/alterarID/{id}")
    public ResponseEntity<?> alterarNinjasPorID(@PathVariable Long id, @RequestBody NinjaDTO ninja){
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
