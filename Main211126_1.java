import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main211126_1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int a = 0 ; a < t ; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); // 초콜릿 바 갯수
            int l = Integer.parseInt(st.nextToken()); // 싼 초콜릿 기준
            int r = Integer.parseInt(st.nextToken()); // 비싼 초콜릿 기준
            int k = Integer.parseInt(st.nextToken()); // 최대 k 달러 만큼만 써야댐
            int cnt = 0;
            int prices[] = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0 ; i < n ; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }
            // 3 1 100 100달러
            // 50 100 50 이 있다면..?
            Arrays.sort(prices);

            for (int i = 0 ; i < n ; i++) {
                if (prices[i] >= l && prices[i] <= r && k >= prices[i]) {
                    k -= prices[i];
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb.toString());
    }
}
