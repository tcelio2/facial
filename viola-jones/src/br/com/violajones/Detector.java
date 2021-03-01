package br.com.violajones;


import br.com.violajones.bean.Pontos;

import java.util.ArrayList;
import java.util.List;

public class Detector {
    private static Integer TAMANHO = 250;

    public static List<Pontos> identificar(int altura, int largura, Integer[][] matriz) {
        //Integer[][] novaMatriz = new Integer[TAMANHO][TAMANHO];
        List<Pontos> pontosList = new ArrayList<>();
        for (int linha = altura; linha < TAMANHO; linha++) {
            for (int coluna = largura; coluna < TAMANHO; coluna++) {
                //System.out.println(matriz[linha][coluna]);
                //Integer media = calcularMedia(matriz, linha, coluna, altura); //altura=largura=2,4 16
                Pontos pontos = extrairValor(matriz, linha, coluna, altura);//altura=largura=2,4 16

                if(pontos.getX1() != 0)
                    pontosList.add(pontos);
            }
        }
       return pontosList;
    }

    private static Pontos extrairValor(Integer[][] matriz, int linha, int coluna, int area) {
        Pontos p = new Pontos();
        Integer somatoria = 0;
        Integer a = matriz[linha-area][coluna-area];
        Integer c = matriz[linha][coluna-area];
        Integer b = matriz[linha-area][coluna];
        Integer d = matriz[linha][coluna];

                //p += matriz[linha-i][coluna-j];
//                System.out.println("a->"+a);
//                System.out.println("b->"+b);
//                System.out.println("c->"+c);
//                System.out.println("d->"+d);
                somatoria = d - b - c + a;
                Integer result = somatoria/(area*area);
       // System.out.println("=>"+result);
                if(result == 0){
                    System.out.println("-->"+result+"posicao: " +
                            "A:"+(linha-area)+"-"+(coluna-area)+" " +
                            "B:"+(linha)+"-"+(coluna-area)+" " +
                            "C:"+(linha-area)+"-"+(coluna)+" " +
                            "D:"+(linha)+"-"+(coluna));

                    p.setX1(linha-area);
                    p.setY1(coluna-area);
                    p.setX2(linha);
                    p.setY2(coluna-area);
                    p.setX3(linha-area);
                    p.setY3(coluna);
                    p.setX4(linha);
                    p.setY4(coluna);
                }
        return p;
    }

    private static Integer calcularMedia(Integer[][] matriz, int linha, int coluna, int area) {
        Integer p = 0;
        for(int i = area; i >= 0; i--) {
            for (int j = area; j >= 0; j--) {
                p = matriz[linha-i][coluna-j];
                //System.out.println("->"+p);
            }
        }
        //System.out.println("\n");
        return p/area;
    }
}
