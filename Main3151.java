import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3151 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int ability[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            ability[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for(int i = 0 ; i < n - 2 ; i++){
            int head = i+1;
            while(head < n - 1){
                int tail = head+1;
                while(tail < n){
                    int sum = ability[i] + ability[head] + ability[tail];
                    if(sum == 0) cnt++;
                    tail++;
                }
                head++;
            }
           
        }
        System.out.println(cnt);
    }
    
}
