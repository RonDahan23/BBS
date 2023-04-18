import java.util.Hashtable;

public class DES
{

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

    static int[][] twoD_arr = {
            {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
            {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
            {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
            {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
    };



    static String[] parts = new String[16];


    public static String encrypt(String plaintext)
    {
        final Integer[] NumberOfLeftShifts = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

        // Convert plaintext to binary and pad with zeros
        String binaryPlaintext = stringToBinary(plaintext);

        // Split the 56-bit key into 28-bit halves
        String leftHalf = binaryPlaintext.substring(0, 28);
        String rightHalf = binaryPlaintext.substring(28, 56);

        // Perform 16 rounds of encryption
        for (int i = 0; i < 16; i++)
        {
            // Left shift the key halves
            leftHalf = leftHalf.substring(NumberOfLeftShifts[i]) + leftHalf.substring(0, NumberOfLeftShifts[i]);
            rightHalf  = rightHalf.substring(NumberOfLeftShifts[i]) + rightHalf.substring(0, NumberOfLeftShifts[i]);

            // Merge the halves and apply PC2
            String mergedKey = leftHalf + rightHalf;
            String permutedKey = Generating48Bits(mergedKey);

            // Perform the expansion and XOR with the key
            String expandedRight = Ffunction(rightHalf);



        }
        return leftHalf;
    }
    public static String stringToBinary(String inputString) {
        StringBuilder binaryString = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            int asciiCode = (int) inputString.charAt(i);
            String binaryRepresentation = Integer.toBinaryString(asciiCode);
            binaryRepresentation = "0".repeat(8 - binaryRepresentation.length()) + binaryRepresentation;
            binaryString.append(binaryRepresentation);
        }

        String paddedBinaryString = binaryString.toString();

        if (paddedBinaryString.length() < 56) {
            paddedBinaryString = "0".repeat(56 - paddedBinaryString.length()) + paddedBinaryString;
        }

        return paddedBinaryString;
    }

    public static Hashtable<Integer, Character> InsertHashTable(String str, Hashtable<Integer, Character> hashTable) {
        for (int i = 0; i < str.length(); i++) {
            hashTable.put(i, str.charAt(i));
        }
        return hashTable;
    }

    public static String Generating56Bits(Hashtable<Integer, Character> hashtable) {
        StringBuilder strGenerating56Bits = new StringBuilder();
        for (int i = 0; i < PC1.length; i++) {
            char value = hashtable.get(PC1[i] - 1);
            strGenerating56Bits.append(value);
        }
        return strGenerating56Bits.toString();
    }

    public static String[] CutInTheMiddle(String str) {
        final int mid = str.length() / 2;
        String[] parts = {str.substring(0, mid), str.substring(mid)};
        return parts;
    }

    public static String Generating48Bits(String str)
    {
        Hashtable<Integer, Character> hashTable48 = new Hashtable<>();
        hashTable48 = InsertHashTable(str, hashTable48);

        StringBuilder strGenerating56Bits = new StringBuilder();
        for (int i = 0; i < PC2.length; i++)
        {
            Character value = hashTable48.get(PC2[i] - 1);
            if (value != null) {
                strGenerating56Bits.append(value.charValue());
            }
        }

        return strGenerating56Bits.toString();

    }
    public static String[] GenerateMtoBits(String str)
    {
        Hashtable<Integer, Character> hashTableForM = new Hashtable<>();
        str = stringToBinary(str);
        hashTableForM = InsertHashTable(str, hashTableForM);

        StringBuilder strGeneratingM = new StringBuilder();
        for (int i = 0; i < IP.length; i++)
        {
            Character value = hashTableForM.get(IP[i] - 1);

            if (value != null)
            {
                strGeneratingM.append(value.charValue());
            }
        }
        String[] parts = CutInTheMiddle(strGeneratingM.toString());

        return parts;
    }
    public static String Ffunction(String rightHalf)
    {
        Hashtable<Integer, Character> hashTableForRight = new Hashtable<>();
        hashTableForRight = InsertHashTable(rightHalf, hashTableForRight);

        StringBuilder strGeneratingTo48 = new StringBuilder();
        for (int i = 0; i < E_TABLE.length; i++)
        {
            Character value = hashTableForRight.get(E_TABLE[i] - 1);

            if (value != null)
            {
                strGeneratingTo48.append(value.charValue());
            }
        }
        return strGeneratingTo48.toString();
    }



}
