import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2480 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int dice[] = new int[7];
        dice[a]++;
        dice[b]++;
        dice[c]++;

        int max = 0;
        int max_idx = 0;
        if (max < dice[a]) {
            max = dice[a];
            max_idx = a;
        }

        if (max < dice[b]) {
            max = dice[b];
            max_idx = b;
        }

        if (max < dice[c]) {
            max = dice[c];
            max_idx = c;
        }

        if (max == 3) {
            System.out.println(10000 + (max_idx * 1000));
        } else if (max == 2) {
            System.out.println(1000 + (max_idx * 100));
        } else {
            max_idx = Math.max(a, Math.max(b, c));
            System.out.println(max_idx * 100);
        }
        
    }
}
