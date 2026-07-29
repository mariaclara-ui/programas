package com.Jogo._02_demo.Classes;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Tenho que estudar mais como funciona a classe controlhe

@RestController
@RequestMapping("/personagens")
public class ControlhePersonagem {

    @GetMapping("/todos")
    public List<Personagem> todos() {

        List<Personagem> personagens = new ArrayList<>();
        try {

            FileReader fr = new FileReader("personagens_db.csv");
            BufferedReader br = new BufferedReader(fr);

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                String tipo = dados[0];
                String nome = dados[1];
                Integer forcaBase = Integer.parseInt(dados[2]);

                if (tipo.equals("L")) {
                    String arteMarcial = dados[3];
                    LutadorCorpoACorpo lutador = new LutadorCorpoACorpo(nome, forcaBase, arteMarcial);personagens.add(lutador);

                } else if (tipo.equals("A")) {
                    String armaPrincipal = dados[3];
                    Atirador atirador = new Atirador(nome, forcaBase, armaPrincipal);personagens.add(atirador);
                }
            }
            br.close();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
        return personagens;
    }
    @GetMapping("/categoria/{tipo}")
    public List<Personagem> buscarPorCategoria(
            @PathVariable String tipo) {

        List<Personagem> personagens = new ArrayList<>();

        try {

            FileReader fr = new FileReader("personagens_db.csv");
            BufferedReader br = new BufferedReader(fr);

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                String categoria = dados[0];
                String nome = dados[1];
                Integer forcaBase = Integer.parseInt(dados[2]);


                if (categoria.equals("L")) {

                    String arteMarcial = dados[3];
                    LutadorCorpoACorpo lutador = new LutadorCorpoACorpo(nome, forcaBase,arteMarcial);

                    if (tipo.equalsIgnoreCase("lutador")) {

                        personagens.add(lutador);
                    }

                } else if (categoria.equals("A")) {
                    String armaPrincipal = dados[3];
                    Atirador atirador = new Atirador(nome, forcaBase, armaPrincipal);

                    if (tipo.equalsIgnoreCase("atirador")) {
                        personagens.add(atirador);
                    }
                }
            }
            br.close();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
        return personagens;
    }
}


