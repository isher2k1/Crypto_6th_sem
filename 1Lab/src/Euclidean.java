import java.math.BigInteger;


public class Euclidean {

    public Euclidean(){}

    public static BigInteger[] gcdExtended(BigInteger a, BigInteger b) {
        BigInteger[] qRemainder;
        BigInteger[] result = new BigInteger[3];
        BigInteger x = a;
        BigInteger y = b;

        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        while (true){
            qRemainder = x.divideAndRemainder(y);
            x = qRemainder[1];
            x0 = x0.subtract(y0.multiply(qRemainder[0]));
            x1 = x1.subtract(y1.multiply(qRemainder[0]));

            if (x.equals(BigInteger.ZERO)) {
                result[0]=y;
                result[1]=y0;
                result[2]=y1;
                return result;
            }

            qRemainder = y.divideAndRemainder(x);
            y = qRemainder[1];
            y0 = y0.subtract(x0.multiply(qRemainder[0]));
            y1 = y1.subtract(x1.multiply(qRemainder[0]));
            if (y.equals(BigInteger.ZERO)) {
                result[0]=x;
                result[1]=x0;
                result[2]=x1;
                return result;
            }
        }
    }

}