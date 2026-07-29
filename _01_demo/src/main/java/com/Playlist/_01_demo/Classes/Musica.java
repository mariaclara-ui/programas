package com.Playlist._01_demo.Classes;

public class Musica {
    private String titulo;
    private String artista;
    private Integer anoLancamento;

    public Musica(String titulo, String artista,Integer anoLancamento) {
        this.titulo = titulo;
        this.artista=artista;
        this.anoLancamento=anoLancamento;
    }

    public String getTitilo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setTitilo(String titilo) {
        this.titulo = titilo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return "titilo = " + titulo + '\'' +
                ", artista = " + artista + '\'' +
                ", ano = " + anoLancamento;
    }
}

