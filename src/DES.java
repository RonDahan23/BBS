import java.util.Hashtable;

public class DES {

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

    public static String Generating56Bits(Hashtable<Integer, Character> hashtable, Integer[] PC1) {
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
    public static String[] Do16LeftShift(String right, String left, Integer[] NumberOfLeftShifts, Integer[] PC2)
    {
        String NewText = "";
        String Text = "";
        for(int i = 0; i < NumberOfLeftShifts.length; i++)
        {

            left = left.substring(NumberOfLeftShifts[i]) + left.substring(0, NumberOfLeftShifts[i]);
            right = right.substring(NumberOfLeftShifts[i]) + right.substring(0, NumberOfLeftShifts[i]);
            Text = left + right;
            NewText += Generating48Bits(Text, PC2);

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
    public static String Generating48Bits(String str, Integer[] PC2)
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


    public static String[] GenerateMtoBits(String str, Integer[] IP)
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


}
