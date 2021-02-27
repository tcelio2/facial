package br.com.violajones;

public class Detector {
    private static Integer TAMANHO = 4;


    public static void detectarQuadrado(Integer[][] image) {
        Integer[][] novaMatriz = new Integer[TAMANHO][TAMANHO];
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                novaMatriz[linha][coluna] = calcularSomatoriaTrecho(linha, coluna, image);

            }
        }
        System.out.println("=>"+novaMatriz);
    }

    private static Integer calcularSomatoriaTrecho(int x, int y, Integer[][] image) {
        Integer somatoria = 0;
        for (int linha = 0; linha <= x ; linha++) {
            for (int coluna = 0; coluna <= y ; coluna++) {
                somatoria += image[linha][coluna];

            }
        }
        return somatoria;
    }


}
