import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main22864 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken()); // 한 번 일 할때 피로도
        int b = Integer.parseInt(st.nextToken()); // 한 번 일 할때 처리량
        int c = Integer.parseInt(st.nextToken()); // 한 번 쉴때 피로도 감소
        int m = Integer.parseInt(st.nextToken()); // 번아웃 한계치

        int S = 0; // 주인공 스테미나
        int ans = 0; // 총 일한 양
        for (int i = 1 ; i <= 24 ; i++) {
            if (S + a > m) {
                // 만약 일을 하여 한계치를 넘는다면 쉰다.
                S -= c;
                if (S < 0) S = 0;
            } else {
                S += a;
                ans += b;
            }
        }

        System.out.println(ans);
    }
    
}
