import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2015 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int num[] = new int[n];
        int ans = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n ; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0 ; i < n - 1 ; i++) {
            for (int j = i ; j < n ; j++) {
                if (num[i] + num[j] == k) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
        
    }
}
