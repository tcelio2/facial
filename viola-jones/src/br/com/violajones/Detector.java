package br.com.violajones;

public class Detector {
    private static Integer TAMANHO = 6;

    public static String identificar(int altura, int largura, Integer[][] matriz) {
        //Integer[][] novaMatriz = new Integer[TAMANHO][TAMANHO];
        for (int linha = altura; linha < TAMANHO; linha = linha + altura) {
            for (int coluna = largura; coluna < TAMANHO; coluna = coluna + largura) {
                //System.out.println(matriz[linha][coluna]);
                Integer media = calcularMedia(matriz, linha, coluna ,altura); //altura=largura=2,4 16
                Integer media2 = extrairValor(matriz, linha, coluna ,altura); //altura=largura=2,4 16
                if(media2 != 0)
                    System.out.println("Media:"+media2);

            }
        }
        return null;
    }

    private static Integer extrairValor(Integer[][] matriz, int linha, int coluna, int area) {
        Integer somatoria = 0;
        Integer a = matriz[linha-area][coluna-area];
        Integer c = matriz[linha][coluna-area];
        Integer b = matriz[linha-area][coluna];
        Integer d = matriz[linha][coluna];

                //p += matriz[linha-i][coluna-j];
                System.out.println("a->"+a);
                System.out.println("b->"+b);
                System.out.println("c->"+c);
                System.out.println("d->"+d);
                somatoria = d - b - c + a;
        System.out.println("\n");
        return somatoria/(area*area);
    }

    private static Integer calcularMedia(Integer[][] matriz, int linha, int coluna, int area) {
        Integer p = 0;
        for(int i = area; i >= 0; i--) {
            for (int j = area; j >= 0; j--) {
                p = matriz[linha-i][coluna-j];
                System.out.println("->"+p);
            }
        }
        System.out.println("\n");
        return p/area;
    }
}
