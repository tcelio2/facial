package br.com.violajones;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application {
    private static Integer TAMANHO = 250;
    private static Integer[][] novaMatriz = new Integer[TAMANHO][TAMANHO];
    private static String caminho = "C:\\Users\\Qintess\\Pictures\\facial\\";

    public static void main(String[] args) throws IOException {
        System.out.println("---");
        File file = new File(caminho+"chaves.png");
        comecar(file);
    }


    private static void comecar(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        try {
            for (int linha = 0; linha < TAMANHO; linha++) {
                for (int coluna = 0; coluna < TAMANHO; coluna++) {
                    int p2 = image.getRGB(linha, coluna);
                    novaMatriz[linha][coluna] = getPixelNormalizado(p2);
                    //matrizPrincipal_procurada[k][numeroFoto] = getPixelNormalizado(p2);
                    //k++;
                }
            }
        remontarFoto(novaMatriz);
        }catch(Exception e){
            System.out.println("Erro: "+e.getCause());
        }
    }

    private static Integer getPixelNormalizado(Integer p2) {
        int r3 = (p2>>16)&0xff;
        int g3 = (p2>>8)&0xff;
        int b3 =  p2&0xff;
        int avg = (b3+g3+r3)/3;
        int pixel = (avg<<16) | (avg<<8) | avg;
        return pixel;
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