import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class ModPowTest {

    static final int NUMBER_OF_TESTS = 100;
    static final int NUMBER_OF_BITS = 512;

    @Test
    public void modPowTest() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            BigInteger a = new BigInteger(NUMBER_OF_BITS, random);
            BigInteger e = new BigInteger(NUMBER_OF_BITS, random);
            BigInteger n = new BigInteger(NUMBER_OF_BITS, random);
            Assert.assertEquals(a.modPow(e, n), ModPow.modPow(a, e, n));
        }
    }
}