import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main10815 {
    static int n, m;
    static int[] sang;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sang = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            sang[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(sang);
        
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < m ; i++) {
            int card = Integer.parseInt(st.nextToken());
            if (check(card)) {
                sb.append(1 + " ");
            } else {
                sb.append(0 + " ");
            }
            
        }

        System.out.print(sb.toString());
        
        
        
    }
    static boolean check(int card) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            long mm = sang[mid];
            if ( mm < card) {
                left = mid + 1;
            }
            else if (mm > card) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
