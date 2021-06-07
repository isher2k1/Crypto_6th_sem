import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class MontgomeryTest {
    static final int NUMBER_OF_BITS = 512;

    public static BigInteger getRandomOdd(int bitLength, Random random) {
        while(true) {
            BigInteger a = new BigInteger(bitLength, random);
            if(a.testBit(0))
                return a;
        }
    }

    @Test
    public void montgomeryTest() {
        Random random = new Random();
        for(int i = 0; i < NUMBER_OF_BITS; i++){
            BigInteger a = new BigInteger(NUMBER_OF_BITS,random);
            BigInteger e = new BigInteger(NUMBER_OF_BITS,random);
            BigInteger n = getRandomOdd(NUMBER_OF_BITS, random);
            Montgomery reducer = new Montgomery(n);
            Assert.assertEquals(a.modPow(e,n), reducer.rebuildOut(reducer.pow(reducer.rebuildIn(a), e)));
        }
    }
}