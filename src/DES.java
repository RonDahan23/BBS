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


    static final Integer[] NumberOfLeftShifts = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};


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
    public static String[] Do16LeftShift(String right, String left)
    {
        String NewText = "";
        for(int i = 0; i < NumberOfLeftShifts.length; i++)
        {

            left = left.substring(NumberOfLeftShifts[i]) + left.substring(0, NumberOfLeftShifts[i]);
            right = right.substring(NumberOfLeftShifts[i]) + right.substring(0, NumberOfLeftShifts[i]);
            String Text = left + right;
            NewText += Generating48Bits(Text);

        }
        String[] parts = new String[16];
        int length = NewText.length();
        int partLength = (int) Math.ceil((double) length / 16);

        for (int i = 0; i < length; i += partLength)
        {
            int partIndex = i / partLength;
            parts[partIndex] = NewText.substring(i, Math.min(length, i + partLength));
        }

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
    public static String Ffunction(String[] parts)
    {
        Hashtable<Integer, Character> hashTableForRight = new Hashtable<>();
        hashTableForRight = InsertHashTable(parts[1], hashTableForRight);

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
