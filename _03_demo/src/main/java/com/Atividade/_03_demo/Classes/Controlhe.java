package com.Atividade._03_demo.Classes;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


//ainda nao entendi como esse classe funciona
@RestController
@RequestMapping("/hackathon")

public class Controlhe {
    @PostMapping("/processar")
    public RelatorioProcessamento processar() {

        int quantidadeAprovados = 0;
        int quantidadeRejeitados = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader("inscricoes_brutas.txt"));

            BufferedWriter aprovados = new BufferedWriter(new FileWriter("aprovados_hackathon.txt"));

            BufferedWriter pendencias = new BufferedWriter(new FileWriter("pendencias_inscricao.txt"));
            String linha;

            while ((linha = br.readLine()) != null) {

                try {

                    String[] dados = linha.split(";");

                    String tipo = dados[0];
                    String nome = dados[1];
                    Integer idade = Integer.parseInt(dados[2]);
                    String matricula = dados[3];
                    String especialidade = dados[4];


                    if (tipo.equalsIgnoreCase("DESENVOLVEDOR")) {

                        Desenvolvedor desenvolvedor =new Desenvolvedor(nome, idade, matricula, especialidade);

                        aprovados.write(linha);
                        aprovados.newLine();

                        quantidadeAprovados++;


                    } else if (tipo.equalsIgnoreCase("DESIGNER")) {

                        Designer designer = new Designer(nome, idade, matricula, especialidade);

                        aprovados.write(linha);
                        aprovados.newLine();

                        quantidadeAprovados++;


                    } else {

                        pendencias.write(linha);
                        pendencias.newLine();

                        quantidadeRejeitados++;
                    }


                } catch (Exception e) {

                    pendencias.write(linha);
                    pendencias.newLine();

                    quantidadeRejeitados++;
                }
            }

            br.close();
            aprovados.close();
            pendencias.close();


            return new RelatorioProcessamento(
                    quantidadeAprovados,
                    quantidadeRejeitados,
                    "Processamento concluído com sucesso!"
            );


        } catch (IOException e) {

            return new RelatorioProcessamento(
                    quantidadeAprovados,
                    quantidadeRejeitados,
                    "Erro ao processar os arquivos: " + e.getMessage()
            );
        }
    }
}

