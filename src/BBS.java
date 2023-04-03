import java.math.BigInteger;
import java.util.Random;


public class BBS {

    public static long RandomSeed(BigInteger N) {

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
            RandomSeed(N);
        }
        return 0;
    }

    public static String generateBBSKeyStream(BigInteger N)
    {
        StringBuilder sb = new StringBuilder();
        long Seed = RandomSeed(N);
        BigInteger bigSeed = BigInteger.valueOf(Seed);

        for (int i = 0; i < 64; i++)
        {
            bigSeed = bigSeed.modPow(BigInteger.valueOf(2), N);
            sb.append(bigSeed.testBit(0) ? '1' : '0');

        }
        return sb.toString();

    }
}