package br.com.violajones;

import br.com.violajones.bean.Pontos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Print2D {

    private static Integer TAMANHO = 250;
    private static String caminho = "/home/hal9000/Pictures/facial/";


    public static void printMatrix(Integer mat[][]) {
        // Loop through all rows
        for (Integer[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public static void printarLocalizacao(Integer[][] matriz, Pontos localizacao) {

        printarX(matriz, localizacao);
        remontarFotoMarcada(matriz);
    }

    private static void printarX(Integer[][] matriz, Pontos ponto) {
        for (int i = ponto.getX1(); i <= ponto.getX2(); i++) {
            matriz[i][ponto.getY1()] = -65536;
        }
        for (int j = ponto.getX3(); j <= ponto.getX4(); j++) {
            matriz[j][ponto.getY3()] = -65536;
        }
        for (int w = ponto.getY1(); w <= ponto.getY3(); w++) {
            matriz[ponto.getX1()][w] = -65536;
        }
        for (int m = ponto.getY2(); m <= ponto.getY4(); m++) {
            matriz[ponto.getX2()][m] = -65536;
        }

    }

    private static void remontarFotoMarcada(Integer[][] foto) {
        BufferedImage image = new BufferedImage(TAMANHO, TAMANHO, BufferedImage.TYPE_INT_RGB);
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                Integer integer = foto[linha][coluna];
                image.setRGB(linha, coluna, integer);
            }
        }
        File f2 = null;
        f2 = new File(caminho+"treeArray.jpg");
        try {
            ImageIO.write(image, "jpg", f2);
        } catch (IOException e) {
            System.out.println("Erro ao criar:" + e.getCause());
        }
    }
}
