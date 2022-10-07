import java.util.*;

public class EncoderDecoder {

    // initial list of mapping
    public static String listOfChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public EncoderDecoder() {
    }

    // encode string
    public String encode(String plainText, String offsetChar) {
        // to determine how much we need to downshift the list
        int offsetVal = listOfChar.indexOf(offsetChar);

        StringBuilder sb = new StringBuilder();
        sb.append(offsetChar);

        // to iterate through the plainText 1 by 1, and determining the final encoded
        // string
        for (int i = 0; i < plainText.length(); i++) {
            String curr = String.valueOf(plainText.charAt(i));
            if (curr.equals(" ")) {
                sb.append(" ");
                continue;
            } else {
                int idxOfCurr = listOfChar.indexOf(curr);
                
                if(idxOfCurr < 0) {
                    sb.append(curr);
                    continue;
                }
                int pos = math(idxOfCurr, offsetVal);

                String encodedChar = String.valueOf(listOfChar.charAt(pos));

                sb.append(encodedChar);
            }
        }

        return sb.toString();
    }

    // decode string
    public String decode(String encodedText) {
        StringBuilder sb = new StringBuilder();
        String offsetChar = String.valueOf(encodedText.charAt(0));

        // to determine how much we need to downshift the lift
        int offsetVal = listOfChar.indexOf(offsetChar);

        if (offsetVal < 0) {
            return null;
        } else {
            // to iterate through the plainText 1 by 1, and determining the final decoded
            // string
            for (int i = 1; i < encodedText.length(); i++) {
                String curr = String.valueOf(encodedText.charAt(i));

                if (curr.equals(" ")) {
                    sb.append(" ");
                    continue;
                } else {
                    int idxOfCurr = listOfChar.indexOf(curr);

                    if(idxOfCurr < 0) {
                        sb.append(curr);
                        continue;
                    }
                    
                    sb.append(String.valueOf(listOfChar.charAt((idxOfCurr + offsetVal) % 44)));
                }
            }

            return sb.toString();
        }
    }

    // to calculate the offset position of a string to be encoded
    // if the value of (i-offset) is negative, then the idx must continue from the
    // bottom of the list
    // else if (i-offset) is non negative, then we can simply return i-offset as the
    // idx
    private int math(int i, int offset) {
        return ((i - offset) < 0) ? 44 + (i - offset) : i - offset;
    }

    // to check if a String of length 1 is a valid offset character
    public static boolean validOffsetChar(String offsetChar) {
        return (listOfChar.indexOf(offsetChar) >= 0 && offsetChar.length() == 1) ? true : false;
    }
}