package P2343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int lecture[] = new int[N];
        int low = 0;
        int high = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < N ; i++) {
            lecture[i] = Integer.parseInt(st.nextToken());
            high += lecture[i];
            low = low < lecture[i] ? lecture[i] : low;
        }
        
        
        while (low <= high) {
            int mid = (low + high) / 2;
            int cnt = 0;
            int sum = 0;
            for (int i = 0 ; i < N ; i++) {
                if (sum + lecture[i] > mid) {
                    cnt++;
                    sum = 0;
                } 
                sum += lecture[i];
                
                
            }
            if (sum > 0) cnt++;
            if (cnt <= M) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low);
    }
}