import java.math.BigInteger;
import java.util.Objects;

public class Montgomery {

    private final BigInteger modul;

    private BigInteger reducer;
    private final int reducerByBits;
    private final BigInteger reciprocal;
    private final BigInteger mask;
    private final BigInteger factor;
    private final BigInteger rebuildedOne;

    public Montgomery(BigInteger moduls) {
        Objects.requireNonNull(moduls);
        if (!moduls.testBit(0) || moduls.compareTo(BigInteger.ONE) <= 0)
            throw new IllegalArgumentException("Moduls must be an odd number at least 3");
        this.modul = moduls;

        reducerByBits = (moduls.bitLength() / 8 + 1) * 8;
        reducer = BigInteger.ONE.shiftLeft(reducerByBits);
        mask = reducer.subtract(BigInteger.ONE);
        assert reducer.compareTo(moduls) > 0 && reducer.gcd(moduls).equals(BigInteger.ONE);

        reciprocal = reducer.modInverse(moduls);
        factor = reducer.multiply(reciprocal).subtract(BigInteger.ONE).divide(moduls);
        rebuildedOne = reducer.mod(moduls);
    }

    public BigInteger rebuildIn(BigInteger x) {
        return x.shiftLeft(reducerByBits).mod(modul);
    }

    public BigInteger rebuildOut(BigInteger x) {
        return x.multiply(reciprocal).mod(modul);
    }

    public BigInteger multiply(BigInteger x, BigInteger y) {
        assert x.signum() >= 0 && x.compareTo(modul) < 0;
        assert y.signum() >= 0 && y.compareTo(modul) < 0;
        BigInteger product = x.multiply(y);
        BigInteger temp = product.and(mask).multiply(factor).and(mask);
        BigInteger reduced = product.add(temp.multiply(modul)).shiftRight(reducerByBits);
        BigInteger result = reduced.compareTo(modul) < 0 ? reduced : reduced.subtract(modul);
        assert result.signum() >= 0 && result.compareTo(modul) < 0;
        return result;
    }

    public BigInteger pow(BigInteger x, BigInteger y) {
        assert x.signum() >= 0 && x.compareTo(modul) < 0;
        if (y.signum() == -1)
            throw new IllegalArgumentException("Exponent < 0");

        BigInteger z = rebuildedOne;
        for (int i = 0, len = y.bitLength(); i < len; i++) {
            if (y.testBit(i))
                z = multiply(z, x);
            x = multiply(x, x);
        }
        return z;
    }

}