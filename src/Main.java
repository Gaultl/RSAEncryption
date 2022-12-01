import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String test = "This is a test string.";
        //int length = test.length();
        long[] testCharArray = new long[test.length()];
        for(int i = 0; i<testCharArray.length; i++){
            System.out.println(testCharArray[i]);
        }

        ArrayList<Character> testArrayList = new ArrayList<Character>();
        for(char c: test.toCharArray()){
            testArrayList.add(c);
        }
        testArrayList.add('&');
        System.out.println(testArrayList.toString());
    }
}
