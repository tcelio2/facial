package br.com.violajones;

import br.com.violajones.bean.Pontos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        //remontarFotoMarcada(matriz);
    }

    public static void printarLocalizacao2(File matriz, Pontos localizacao) throws IOException {

        printar(matriz, localizacao);
        //remontarFotoMarcada(matriz);
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

    public static void printar(File myPicFile, Pontos local) throws IOException {
        BufferedImage myPicture = ImageIO.read(myPicFile);
        Graphics2D g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.RED);
        int x = local.getX1()-10;
        int y = local.getY1()-10;

        int width = local.getX4() - local.getX3() +20;
        int heigt = local.getY4() - local.getY2() +20;
        g.drawRect(x, y, width, heigt);


        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
        //1A    2B

        //3C     4D
    }


    private static void remontarFotoMarcada(File foto) {
        BufferedImage image = new BufferedImage(TAMANHO, TAMANHO, BufferedImage.TYPE_INT_RGB);
        File f2 = null;
        f2 = new File(caminho+"treeArray.jpg");
        try {
            ImageIO.write(image, "jpg", foto);
        } catch (IOException e) {
            System.out.println("Erro ao criar:" + e.getCause());
        }
    }
}
