import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14719 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int h = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] rain = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n ; i++) {
            rain[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0; 
        for (int i = 1 ; i < n - 1 ; i++) {
            int left = 0;
            int right = 0;
            // 기준점(i) 왼쪽에서 가장 높은 블록 찾기
            for (int j = 0 ; j < i ; j++) {
                left = Math.max(left, rain[j]);
            }
            // 기준점(i) 오른쪽에서 가장 높은 블록 찾기
            for (int j = i + 1 ; j < n ; j++) {
                right = Math.max(right, rain[j]); 
            }

            int height = Math.min(left, right);
            if (rain[i] < height) {
                ans += height - rain[i];
            }
        }

        System.out.println(ans);

    }
    
}
