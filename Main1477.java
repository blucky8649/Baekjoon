import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main1477 {
    static int N, M, L;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 휴게소의 개수
        M = Integer.parseInt(st.nextToken()); // 지을 휴게소 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로 길이

        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[N + 1] = L;
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 1;
        int right = L;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 1 ; i < arr.length ; i++) {
                cnt += (arr[i] - arr[i-1] - 1) / mid;
            }

            if (cnt > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }
}
