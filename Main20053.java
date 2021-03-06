import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main20053 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < t ; i++) {
            int n = Integer.parseInt(br.readLine());
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num < min) {
                    min = num;
                }
                if (num > max) {
                    max = num;
                }

            }
            sb.append(min + " " + max).append("\n");
        }

        System.out.print(sb.toString());
    }
    
}
