package juegoCraps;

import java.util.Random;

/**
 * Class dando generates a random number between 1 to 6
 * @author ALEJANDRO LASSO MEDINA
 * @version v.1.0.0 7/12/2021
 */

public class Dado {
    /**
     * Method that generates a random value to cara
     * @return number between 1 - 6
     */
    private int cara;

    public int getCara() {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        return cara;
    }
}
