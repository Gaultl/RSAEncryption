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

            int keyFind1 = 1697432123;//11
            int keyFind2 = 1073741789;//13
            int n = keyFind1 * keyFind2;
            BigInteger e = new BigInteger("1672773"); // must be relatively prime to (keyFind1 - 1)(keyFind2 - 1)      7
            BigInteger d = new BigInteger("2447384868165838171");  //223
            // find d with this equation: e * d mod ((keyFind1 - 1)(keyFind2 - 1)) = 1
            //private key is e and d, public key is e and n
            int[] cTextArray;
            BigInteger bigN = new BigInteger(String.valueOf(n));

            if (response.equals("encrypt")) {
                System.out.println("Enter the text you wish to encrypt");
                String pText = input.nextLine();

                char[] pTextArray = pText.toCharArray();

                int[] pTextMath = new int[pTextArray.length];

                for(int i = 0; i < pTextArray.length; i++){
                    pTextMath[i] = pTextArray[i];
                }


                cTextArray = new int[pText.length()];

                for (int i = 0; i < pTextArray.length; i++) {
                    BigInteger pTextASCII = new BigInteger(String.valueOf(pTextMath[i]));
                    System.out.println(pTextASCII);
                    BigInteger cTextMath = pTextASCII.modPow(e, bigN);
                    System.out.println(cTextMath);
                    int cText = cTextMath.intValue();
                    cTextArray[i] = cText;
                }

                String encrypted = "";

                for(int i = 0; i < cTextArray.length; i++){
                    String temp = String.valueOf(cTextArray[i]);
                    if (temp.length() == 1){
                        encrypted += "00" + temp;
                    } else if (temp.length() == 2){
                        encrypted += "0" + temp;
                    } else {
                        encrypted += temp;
                    }
                }

                System.out.println(encrypted);

                System.out.println();

            } else if (response.equals("decrypt")) {
                System.out.println("Enter the number to decrypt");
                String decrypt = input.nextLine();
                int length = decrypt.length()/3;
                cTextArray = new int[length];
                int index = 0;

                for(int i = 0; i < decrypt.length(); i += 3){
                    String val = decrypt.substring(i, i+3);
                    cTextArray[index] = Integer.parseInt(val);
                    index++;
                }

                char[] fTextArray = new char[length];

                for (int i = 0; i < cTextArray.length; i++) {
                    BigInteger unencrypt = new BigInteger(String.valueOf(cTextArray[i]));
                    BigInteger calculated = unencrypt.modPow(d, bigN);
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