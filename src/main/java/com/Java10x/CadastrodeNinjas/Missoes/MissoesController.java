package com.Java10x.CadastrodeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listarMissao(){
        return "Missoes listadas";
    }

    @PostMapping("/criar")
    public String criarMissao(){
        return "missao criada";
    }

    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada";
    }

    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao Deletada";
    }
}
