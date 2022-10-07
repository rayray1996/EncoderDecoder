import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the offset character: ");
        String offset = sc.nextLine();
        // if user input for offset is not a valid offset char, then disallow the input
        // and get them to re-enter an offset input that is valid
        while (!EncoderDecoder.validOffsetChar(offset)) {
            if (offset.length() > 1) {
                System.out.println("Only 1 character can be used as the offset character.");
            } else {
                System.out.println("Offset character is invalid.");
            }
            System.out.println("Enter the offset character again: ");
            offset = sc.nextLine();
        }

        System.out.println("Encode or Decode? (input only E or D): ");
        String choice = sc.nextLine();
        // if user choice is not D or E, then disallow the input and force them to
        // re-enter a valid choice
        while (!(choice.equals("D") || choice.equals("E"))) {
            System.out.println("Please enter a valid input: ");
            choice = sc.nextLine();
        }

        System.out.println("Enter the string to encode or decode: ");
        String input = sc.nextLine();
        // if a user choice is empty, null or the first character
        // of the input decoded string is not a valid offset character then force them
        // to re-enter a valid input
        while (input == null ||
                input.equals("") ||
                (choice.equals("D") && !EncoderDecoder.validOffsetChar(String.valueOf(input.charAt(0))))) {
            if (input == null || input.equals("")) {
                System.out.println("Enter the string to encode or decode: ");
            } else {
                System.out.println("Offset character of the encoded text is invalid");
                System.out.println("Enter the string to encode or decode: ");
            }
            input = sc.nextLine();
        }

        EncoderDecoder encoderDecoder = new EncoderDecoder();

        if (choice.equals("D")) {
            System.out.println("Your decoded string is: " + encoderDecoder.decode(input));
        } else {
            System.out.println("Your encoded string is: " + encoderDecoder.encode(input, offset));
        }
    }

}
