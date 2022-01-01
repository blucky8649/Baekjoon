import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1159 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int alp[] = new int[26];
        for (int i = 0 ; i < n ; i++) {
            String name = br.readLine();
            alp[name.charAt(0)-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 26 ; i++) {
            if (alp[i] >= 5) {
                sb.append((char)(i + 'a'));
            }
        }
        if (sb.toString().length() == 0) {
            System.out.print("PREDAJA");
        } else {
            System.out.print(sb.toString());
        }
    }
}
