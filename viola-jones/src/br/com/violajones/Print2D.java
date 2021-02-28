package br.com.violajones;

import java.util.Arrays;

public class Print2D {

    public static void printMatrix(Integer mat[][])
    {
        // Loop through all rows
        for (Integer[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
}
