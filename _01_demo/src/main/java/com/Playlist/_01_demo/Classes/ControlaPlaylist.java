package com.Playlist._01_demo.Classes;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/playlist")
public class ControlaPlaylist {

    @GetMapping("/listar")
    public List<Musica> listar() {
        List<Musica> playlist = new ArrayList<>();
        try {
            FileReader fr = new FileReader("minha_playlist.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                String titulo = dados[0];
                String artista = dados[1];
                int ano = Integer.parseInt(dados[2]);

                Musica musica = new Musica(titulo, artista, ano);

                playlist.add(musica);
            }
            br.close();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
        return playlist;
    }
    @PostMapping("/adicionar")
    public String adicionar(@RequestBody Musica novaMusica) {

        try {

            FileWriter fw = new FileWriter("minha_playlist.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(novaMusica.getTitilo() + ";" + novaMusica.getArtista() + ";" + novaMusica.getAnoLancamento());

            bw.newLine();
            bw.close();

            return "Música salva com sucesso!";

        } catch (IOException e) {

            return "Erro ao salvar música!";
        }
    }
}

