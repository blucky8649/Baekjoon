package P16564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int low = Integer.MAX_VALUE;
        int high = Integer.MAX_VALUE;
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            low = Math.min(low, arr[i]);
        }
        int answer = 0;
        while (low < high) {
            int mid = (low + high) / 2;

            long sum = 0;
            for (int i = 0 ; i < N ; i++) {
                if (arr[i] < mid) {
                    sum += mid - arr[i];
                }
            }

            if (sum <= K) {
                low = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                high = mid;
            }
        }
        System.out.println(answer);
    }
    
}
