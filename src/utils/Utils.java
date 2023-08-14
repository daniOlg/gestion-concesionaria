package utils;

import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    public static int generarCodigo(int min, int max) {
        return random.nextInt(min, max);
    }
}
