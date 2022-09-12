package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiteController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/")
    public String formulario(){
        return "index";
    }

    @PostMapping("/cadastro")
    public String cadastrar( @ModelAttribute FormularioDeCadastro cadastro ){

        UsuarioEntity usuario = UsuarioEntity.builder()
                .email( cadastro.getEmail() )
                .nome( cadastro.getNome() )
                .build();

        repository.save( usuario );

        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public String listar( Model model ){
        model.addAttribute("usuarioList" ,  repository.findAll() );
        return "listar";
    }


}
