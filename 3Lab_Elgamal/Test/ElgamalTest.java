import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ElgamalTest {

    static final Integer n = 100;
    static final Integer a = 0;
    static final Integer[] array = new Integer[n];

    public static final int NUMBER_OF_TESTS = array.length;

    @Test
    void run() {

        for (int i = 0; i<n; i++){
            int rnd = a + (int) (Math.random() * n);
            array[i] = rnd;
            System.out.println(array[i]);
        }

        for(int i = 0; i < NUMBER_OF_TESTS; i++){
            BigInteger number = new BigInteger(String.valueOf(array[i]));
            Assert.assertEquals(number, new Elgamal().run(number));
        }
    }
}