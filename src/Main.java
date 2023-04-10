import java.math.BigInteger;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Main

{


    static Hashtable<Integer, Character> hashTable56 = new Hashtable<>();


    public static void main(String[] args)
    {

        DES d = new DES();
        BBS b = new BBS();
        String str = "abcd";
        String a = b.generateBBSKeyStream();
        hashTable56 = d.InsertHashTable(a, hashTable56);


        String strGenerating56Bits = d.Generating56Bits(hashTable56);
        String[] parts = d.CutInTheMiddle(strGenerating56Bits);
        String left = parts[0];
        String right = parts[1];
        parts =  d.Do16LeftShift(right, left);




    }

}

