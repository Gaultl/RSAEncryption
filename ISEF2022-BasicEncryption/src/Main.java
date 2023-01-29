import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String response = "";


        while(!response.equals("done")) {
            System.out.println("Do you want to encrypt or decrypt? enter done to finish");
            response = input.nextLine().toLowerCase();
            while (!response.equals("encrypt") && !response.equals("decrypt") && !response.equals("done")) {
                System.out.println("Please type 'encrypt', 'decrypt', or 'done'");
                response = input.nextLine().toLowerCase();
            }

            String encrypted = "";
            BigInteger keyFind1 = new BigInteger("1697432123");//11
            BigInteger keyFind2 = new BigInteger("1073741789");//13
            BigInteger n = keyFind1.multiply(keyFind2);
            BigInteger e = new BigInteger("1672771"); // must be relatively prime to (keyFind1 - 1)(keyFind2 - 1)      7
            BigInteger d = new BigInteger("18850819083330065395");  //223
            // find d with this equation: e * d mod ((keyFind1 - 1)(keyFind2 - 1)) = 1
            //private key is e and d, public key is e and n
            String[] cTextArray;

            if (response.equals("encrypt")) {
                System.out.println("Enter the text you wish to encrypt");
                String pText = input.nextLine();

                char[] pTextArray = pText.toCharArray();

                int[] pTextMath = new int[pTextArray.length];

                for(int i = 0; i < pTextArray.length; i++){
                    pTextMath[i] = pTextArray[i];
                }


                for (int i = 0; i < pTextArray.length; i++) {
                    BigInteger pTextASCII = new BigInteger(String.valueOf(pTextMath[i]));
                    BigInteger cTextMath = pTextASCII.modPow(e, n);
                    String cText = String.valueOf(cTextMath);
                    if (cText.length() == String.valueOf(n).length()-2){
                        encrypted += "00" + cText;
                    } else if (cText.length() == String.valueOf(n).length()-1){
                        encrypted += "0" + cText;
                    } else {
                        encrypted += cText;
                    }
                }

                System.out.println(encrypted);

                System.out.println();

            } else if (response.equals("decrypt")) {
                System.out.println("Enter the number to decrypt");
                String decrypt = input.nextLine();
                int length = decrypt.length()/String.valueOf(n).length();
                cTextArray = new String[length];
                int index = 0;

                for(int i = 0; i < decrypt.length(); i += String.valueOf(n).length()){
                    String val = decrypt.substring(i, i+String.valueOf(n).length());
                    cTextArray[index] = val;
                    index++;
                }

                char[] fTextArray = new char[length];

                for (int i = 0; i < cTextArray.length; i++) {
                    BigInteger unencrypt = new BigInteger(String.valueOf(cTextArray[i]));
                    BigInteger calculated = unencrypt.modPow(d, n);
                    char fText = (char) calculated.intValue();
                    fTextArray[i] = fText;
                }
                System.out.println(fTextArray);
            } else {
                break;
            }
        }
    }
}
