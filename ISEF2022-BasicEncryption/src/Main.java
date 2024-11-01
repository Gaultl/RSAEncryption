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
            BigInteger keyFind1 = new BigInteger("179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137859");
            BigInteger keyFind2 = new BigInteger("359538626972463181545861038157804946723595395788461314546860162315465351611001926265416954644815072042240227759742786715317579537628833244985694861278948248755535786849730970552604439202492188238906165904170011537676301364684925762947826221081654474326701021369172596479894491876959432609670712659248448275913");
            BigInteger n = keyFind1.multiply(keyFind2);
            BigInteger nLength = new BigInteger(String.valueOf(String.valueOf(n).length()));
            BigInteger e = new BigInteger("65537"); // must be relatively prime to (keyFind1 - 1)(keyFind2 - 1)
            BigInteger d = new BigInteger("43256670378374722407698100506332385154555099781692504848658592269913450388204664921083432293538200022303457847749984559349118146509595565563015135017532329705479070210940048814570560859662191771249275495587640191409078337485205155103662989022381351389602257311630510260954635280189288109963405580907722680326336685099804251720698972379248094220676153515349793199907705184341320434637020919383387951413500161264731848385480198875681564458197252459236343976934741171168731136463124766453556963866434344149043757803548797822819113120560640106538407750765458223558920321927242575789647337134025625024631542695465221130961");
            // find d with this equation: e * d mod ((keyFind1 - 1)(keyFind2 - 1)) = 1
            //private key is d, public key is e and n
            String[] cTextArray;

            if (response.equals("encrypt")) {
                System.out.println("Enter the text you wish to encrypt");
                String pText = input.nextLine();

                long start = System.currentTimeMillis();

                char[] pTextArray = pText.toCharArray();

                int[] pTextMath = new int[pTextArray.length];

                for(int i = 0; i < pTextArray.length; i++){
                    pTextMath[i] = pTextArray[i];
                }


                for (int i = 0; i < pTextArray.length; i++) {
                    BigInteger pTextASCII = new BigInteger(String.valueOf(pTextMath[i]));
                    BigInteger cTextMath = pTextASCII.modPow(e, n);
                    String cText = String.valueOf(cTextMath);
                    BigInteger chrLength = new BigInteger(String.valueOf(cText.length()));
                    String filler = "";
                    if(!chrLength.equals(nLength)) {
                        int lengthDiff = Integer.parseInt(String.valueOf(nLength.subtract(chrLength)));
                        for (int j = 0; j < lengthDiff; j++) {
                            filler += "0";
                        }
                    }
                    encrypted += filler + cText;
                }

                System.out.println(encrypted);

                long elapsedTime = System.currentTimeMillis() - start;
                System.out.println("\nElapsed Time: " + elapsedTime + " milliseconds");

                System.out.println();

            } else if (response.equals("decrypt")) {
                System.out.println("Enter the number to decrypt");
                String decrypt = input.nextLine();

                long start = System.currentTimeMillis();

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

                long elapsedTime = System.currentTimeMillis() - start;
                System.out.println("\nElapsed Time: " + elapsedTime + " milliseconds\n");

            } else {
                break;
            }
        }
    }
}
