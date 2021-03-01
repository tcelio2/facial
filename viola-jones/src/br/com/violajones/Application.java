package br.com.violajones;


import br.com.violajones.bean.Pontos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Application {
    private static Integer TAMANHO = 250;
    private static Integer[][] novaMatriz = new Integer[TAMANHO][TAMANHO];
    private static Integer[][] novaMatrizSemAlteracao = new Integer[TAMANHO][TAMANHO];

    //private static String caminho = "C:\\Users\\Qintess\\Pictures\\facial\\";
    private static String caminho = "/home/hal9000/Pictures/facial/";
    private static String foto = "ponto.png";//ponto.png chaves.png black.png green.png red.png yellow.png blue.png

    public static void main(String[] args) throws IOException {
        System.out.println("---");
        File file = new File(caminho+foto);

        Integer[][] novaMatrizSemAlteracao = normalizarFotoSemAlterar(file);

        Integer[][] novaMatriz = normalizarFoto(file);
        Print2D.printMatrix(novaMatriz);
        remontarFoto(novaMatrizSemAlteracao);
        //Integer[][] matrizNormalizada = Normalizador.normalizarMatriz(montarExemplo());
        Integer[][] matrizNormalizada = Normalizador.normalizarMatriz(novaMatriz);
        //Print2D.printMatrix(matrizNormalizada);

        List<Pontos> identificado = Detector.identificar(16, 16, matrizNormalizada);
        Print2D.printarLocalizacao(novaMatrizSemAlteracao, identificado.get(0));
    }

    private static Integer[][] montarExemplo() {
        Integer[][] value = {{1, 2, 3, 4, 6, 0},
                             {5, 0, 0, 7, 4, 2},
                             {7, 0, 0, 1, 1, 9},
                             {1, 2, 10,3, 8, 7},
                             {1, 2, 10,3, 8, 7},
                             {5, 0, 0, 7, 4, 2}
                            };
        return value;
    }


    private static Integer[][] normalizarFotoSemAlterar(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        try {
            for (int linha = 0; linha < TAMANHO; linha++) {
                for (int coluna = 0; coluna < TAMANHO; coluna++) {
                    int p2 = image.getRGB(linha, coluna);

                    novaMatrizSemAlteracao[linha][coluna] = getPixelSemAlterar(p2);
                }
            }
            return novaMatrizSemAlteracao;

        }catch(Exception e){
            System.out.println("Erro: "+e.getCause());
        }
        return null;
    }

    private static Integer[][] normalizarFoto(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        try {
            for (int linha = 0; linha < TAMANHO; linha++) {
                for (int coluna = 0; coluna < TAMANHO; coluna++) {
                    int p2 = image.getRGB(linha, coluna);

                    novaMatriz[linha][coluna] = getPixelNormalizado(p2);
                }
            }
            return novaMatriz;

        }catch(Exception e){
            System.out.println("Erro: "+e.getCause());
        }
        return null;
    }


    private static Integer getPixelSemAlterar(Integer p2) {
        int a3 = (p2>>24)&0xff;
        int r3 = (p2>>16)&0xff;
        int g3 = (p2>>8)&0xff;
        int b3 =  p2&0xff;

        int pixel = (a3<<24) | (r3<<16) | (g3<<8) | b3;
        return pixel;
    }

    private static Integer getPixelNormalizado(Integer p2) {
        Color c = new Color(p2);
        int red   = c.getRed();
        int green = c.getGreen();
        int blue  = c.getBlue();
      //  System.out.println(red+"-"+blue+"-"+green);
        int pixelRgb = (red+green+blue) / 3;


        int a3 = (p2>>24)&0xff;

        int r3 = (p2>>16)&0xff;
        int g3 = (p2>>8)&0xff;
        int b3 =  p2&0xff;

        int avg = (r3+g3+b3)/3;
        int pixel = (a3<<24) | (avg<<16) | (avg<<8) | avg;
       // int pixel2 = (a3<<24) | (r3<<16) | (g3<<8) | b3;
        return pixelRgb;
    }

    private static Integer getPixelNormalizado2(Integer p2) {

        int r = (p2>>16)&0xff;
        int g = (p2>>8)&0xff;
        int b =  p2&0xff;

        float rr = (float) Math.pow(r / 255.0, 2.2);
        float gg = (float) Math.pow(g / 255.0, 2.2);
        float bb = (float) Math.pow(b / 255.0, 2.2);

        float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);

        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
        int pixelGrey = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
        return pixelGrey;
    }

    private static void remontarFoto(Integer[][] foto) {
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