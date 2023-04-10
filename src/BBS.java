import java.math.BigInteger;
import java.util.Random;


public class BBS
{
    static BigInteger p = new BigInteger("30000000091");
    static BigInteger q = new BigInteger("40000000003");
    static BigInteger N = new BigInteger("1200000003730000000273");

    static BigInteger phi = new BigInteger("1200000003660000000180");

    public static long RandomSeed() {

        Random rand = new Random();
        long Seed = rand.nextInt(9 - 1) + 1;
        for (int i = 1; i < 9; i++)
        {
            int number = rand.nextInt(9 - 1) + 1;
            Seed = Seed * 10 + number;

        }
        BigInteger bInt = BigInteger.valueOf(Seed);
        if (N.gcd(bInt).intValue() == 1)
        {
            return Seed;
        }
        else
        {
            RandomSeed();
        }
        return 0;
    }

    public static String generateBBSKeyStream()
    {
        StringBuilder sb = new StringBuilder();
        long Seed = RandomSeed();
        BigInteger bigSeed = BigInteger.valueOf(Seed);

        for (int i = 0; i < 64; i++)
        {
            bigSeed = bigSeed.modPow(BigInteger.valueOf(2), N);
            sb.append(bigSeed.testBit(0) ? '1' : '0');

        }
        return sb.toString();

    }
}