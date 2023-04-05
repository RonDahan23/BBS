import java.math.BigInteger;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Main

{

    static BigInteger p = new BigInteger("30000000091");
    static BigInteger q = new BigInteger("40000000003");
    static BigInteger N = new BigInteger("1200000003730000000273");

    static BigInteger phi = new BigInteger("1200000003660000000180");


    static Hashtable<Integer, Character> hashTable56 = new Hashtable<>();

    static final Integer[] PC1 = {
            57,49,41,33,25,17,9,1,
            58,50,42,34,26,18,10,2,
            59,51,43,35,27,19,11,3,
            60,52,44,36,63,55,47,39,
            31,23,15,7,62,54,46,38,
            30,22,14,6,61,53,45,37,
            29,21,13,5,28,20,12,4
    };


    static final Integer[] PC2 = {
            14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12, 4,
            26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32
    };

    static final Integer[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    static final Integer[] E_TABLE = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };


    static final Integer[] NumberOfLeftShifts = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

    public static void main(String[] args)
    {

        DES d = new DES();
        BBS b = new BBS();
        String str = "abcd";
        String a = b.generateBBSKeyStream(N);
        hashTable56 = d.InsertHashTable(a, hashTable56);


        String strGenerating56Bits = d.Generating56Bits(hashTable56, PC1);
        String[] parts = d.CutInTheMiddle(strGenerating56Bits);
        String left = parts[0];
        String right = parts[1];
        d.Do16LeftShift(right, left, NumberOfLeftShifts, PC2);


    }

}

