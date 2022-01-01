import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Deviation {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[];
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr= new int[n];
            int min = Integer.MAX_VALUE;
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);

            

            while (true) {
                int m = Math.abs(arr[0] + arr[2] - (arr[1] * 2));
                if (m > min) {
                    break;
                }
                if (min == 0) {
                    min = m;
                    break;
                }
                arr[0]++;
                arr[1]--;
            }
            System.out.println(min);
        }
    }
}
