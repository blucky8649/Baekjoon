import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main11508 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer milks[] = new Integer[n + 1];
        milks[0] =100001;
        for(int i = 1 ; i <= n ; i ++){
            milks[i] = Integer.parseInt(br.readLine());
        }
        long answer = 0;
        Arrays.sort(milks, Collections.reverseOrder());
        for(int i = 1 ; i <= n ; i++){
            if(i % 3 == 0) continue;
            answer += milks[i];
        }

        System.out.println(answer);
    }
    
}
