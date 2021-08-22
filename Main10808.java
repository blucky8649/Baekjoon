import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10808 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int[] cnt = new int[26];
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length(); i++){
            cnt[str.charAt(i) - 'a']++;
        }

        for(int i = 0 ; i < cnt.length ; i++){
            sb.append(cnt[i]).append(" ");
        }
        
        System.out.print(sb.toString());
    }
}