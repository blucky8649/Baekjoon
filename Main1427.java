import java.io.*;
import java.util.Arrays;

public class Main1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();
        int[] num = new int[n.length()];
        for(int i = 0 ; i < n.length();i++){
            num[i] = n.charAt(i) -48;
            
        }
        Arrays.sort(num);
        for(int i = num.length-1; i>=0 ; i--){
            System.out.print(num[i]);
        }
    }
}
